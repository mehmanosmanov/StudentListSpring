package com.example.StudentList.service;

import com.example.StudentList.dto.request.AccountBalanceRequest;
import com.example.StudentList.dto.request.StudentRequest;
import com.example.StudentList.dto.response.StudentGroupResponse;
import com.example.StudentList.dto.response.StudentResponse;
import com.example.StudentList.dto.response.TeacherResponse;
import com.example.StudentList.entity.*;
import com.example.StudentList.exception.AgeLimitException;
import com.example.StudentList.exception.NotFoundException;
import com.example.StudentList.mapper.StudentMapper;
import com.example.StudentList.mapper.TeacherMapper;
import com.example.StudentList.repository.AccountBalanceRepository;
import com.example.StudentList.repository.GroupRepository;
import com.example.StudentList.repository.StudentRepository;
import com.example.StudentList.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.SpringProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final AccountBalanceRepository accountBalanceRepository;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;


    //    @Value("${file.directory.path}")
//    private String path
    /**
     * ??????????????????????????????????????????????????????
     **/
    private final Path path1 = Paths.get("D:\\JAVA\\project_files\\upload_to");
    private final FileUtil fileUtil;
    private final File root = new File("D:\\JAVA\\project_files\\upload_to");



    public String saveStudentAllData(StudentRequest request){
        //convertor adds student infos and students field to the entity class

        final Student student = studentMapper.dtoToStudent(request);

        final StudentInfo studentInfo = new StudentInfo();
        studentInfo.setAddress(request.getAddress());
        studentInfo.setPhoneNumber(request.getPhoneNumber());
        Optional<StudentGroup> studentGroup = groupRepository.findByNameAndNumber(request.getGroupName(), request.getGroupNumber());
        if (studentGroup.isEmpty()) {
            StudentGroup group = new StudentGroup();
            group.setName(request.getGroupName());
            group.setNumber(request.getGroupNumber());
            student.setStudentGroup(group);
        } else {
            student.setStudentGroup(studentGroup.get());
        }
        student.setStudentInfo(studentInfo);
        final Teacher teacher = teacherMapper.dtoToEntity(request);
        teacher.addStudent(student);

        studentRepository.save(student);
        System.out.println(teacher);

        log.info("New student saved {}", request);
        return "Student saved successfully";
    }

    public String save(StudentRequest request) {
        log.info("Starting to save a new student");
        if (request.getAge() < 16) throw new AgeLimitException();
        studentRepository.save(studentMapper.dtoToStudent(request));
        log.info("New student saved {}", request);
        return "Student saved successfully";
    }

    @Override
    public ResponseEntity<List<StudentGroupResponse>> getAll() {
        log.info("Start to get all possible students");
        List<StudentGroup> studentGroups = groupRepository.findAll();
//??????????????????????????????????
//        System.out.println(studentGroups.get(3).getStudents().stream().toList().get(3).getSurname());
        List<StudentGroupResponse> responses = new ArrayList<>();
        for (var group : studentGroups) {
            StudentGroupResponse groupResponse = new StudentGroupResponse();
            groupResponse.setName(group.getName());
            groupResponse.setNumber(group.getNumber());

            for (var student : group.getStudents()) {
                StudentResponse studentResponse = studentMapper.studentToDto(student);
                groupResponse.getStudents().add(studentResponse);
            }
            responses.add(groupResponse);
        }
//        if (studentResponses.isEmpty()) log.warn("Student list is empty");
        log.info("All students are fetched");
        return ResponseEntity.ok(responses);
    }

    @Override
    public StudentResponse getById(Long id) {
        log.info("Checking of {} id student", id);
        return studentMapper.studentToDto(studentRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Not found student with such id=" + id)));
//    log.info("{} id student found", id);
    }

    public List<StudentResponse> getByName(String name) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student sstudent : studentRepository.getByName(name)) {
            responses.add(studentMapper.studentToDto(sstudent));
        }
        return responses;
    }

    @Override
    public String update(StudentRequest request, Long id, MultipartFile image) throws IOException {
        Student oldStudent = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found student with such id=" + id));
        log.info("Updating {} id student", id);
        oldStudent = studentMapper.dtoToStudent(request);

        oldStudent.setName(request.getName());
        oldStudent.setSurname(request.getSurname());
        oldStudent.setAge(request.getAge());
        oldStudent.getStudentInfo().setAddress(request.getAddress());
        oldStudent.getStudentInfo().setPhoneNumber(request.getPhoneNumber());
        oldStudent.getStudentInfo().setImage(image.getOriginalFilename());
        Files.copy(image.getInputStream(), this.path1.resolve(Objects.requireNonNull(image.getOriginalFilename())));
        studentRepository.save(oldStudent);
        log.info("{} id student is updated", id);
        return "Student with id=" + id + " successfully updated ";
    }

    @Override
    public String deleteById(Long id) {
        studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found student with such id=" + id));
        log.info("Starting to delete {} id student", id);
        studentRepository.deleteById(id);

        log.info("{} id student is deleted", id);
        return "Student id=" + id + " is successfully deleted";
    }

    public ResponseEntity<Resource> getStudentImage(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found student with such id=" + id));
        return getFile(student.getStudentInfo().getImage());
    }

    public ResponseEntity<Resource> getFile(String fileName) {
        Resource resource = fileUtil.load(fileName, this.path1);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(resource);
    }


    /**
     * This part belongs to the money transaction
     **/

    @Transactional
    public String transaction(Long from, Long to, Double amount) {
        accountBalanceRepository.subtractAccountBalance(from, amount);
        accountBalanceRepository.addAccountBalance(to, amount);
        return "Transaction successful";
    }

    public void creatBalanceAccount(AccountBalanceRequest request) {
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setName(request.getName());
        accountBalance.setSurName(request.getSurName());
        accountBalance.setAccountNum(request.getAccountNum());
        accountBalanceRepository.save(accountBalance);
    }
}