package com.example.StudentList.service;

import com.example.StudentList.model.dto.request.StudentRequest;
import com.example.StudentList.model.dto.response.StudentGroupResponse;
import com.example.StudentList.model.dto.response.StudentResponse;
import com.example.StudentList.exception.AgeLimitException;
import com.example.StudentList.exception.NotFoundException;
import com.example.StudentList.mapper.StudentMapper;
import com.example.StudentList.mapper.TeacherMapper;
import com.example.StudentList.model.entity.Student;
import com.example.StudentList.model.entity.StudentGroup;
import com.example.StudentList.model.entity.Teacher;
import com.example.StudentList.repository.GroupRepository;
import com.example.StudentList.repository.StudentRepository;
import com.example.StudentList.repository.TeacherRepository;
import com.example.StudentList.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

   private final StudentRepository studentRepository;
   private final GroupRepository groupRepository;
   private final TeacherRepository teacherRepository;
   private final StudentMapper studentMapper;
   private final TeacherMapper teacherMapper;

   /**
    * Solved the file transformation problem
    **/
   @Value("${file.directory}")
   private String rootPath;
   private Path path;

   @PostConstruct
   public void init() {
      path = Paths.get(rootPath);
   }

   private final FileUtil fileUtil;


   @Override
   public String saveStudentAllData(StudentRequest request, MultipartFile file) throws IOException {
      final Student student = studentMapper.dtoToStudent(request);
      Files.copy(file.getInputStream(), path.resolve(UUID.randomUUID().toString().substring(0, 5) + "-" + file.getOriginalFilename()));
      student.getStudentInfo().setImage(file.getOriginalFilename());
      //checking if the group already has
      Optional<StudentGroup> studentGroup = groupRepository.findByNameAndNumber(request.getGroupName(), request.getGroupNumber());
      if (studentGroup.isEmpty()) {
         //if not create new one
         student.setStudentGroup(new StudentGroup(request.getGroupName(), request.getGroupNumber()));
      } else {
         student.setStudentGroup(studentGroup.get());
      }
      //checking if the teacher already has
      Optional<Teacher> teacherOptional = teacherRepository.findByNameAndSurName(request.getTeacherName(), request.getTeacherSurname());
      //if not create new one
      if (teacherOptional.isEmpty()) {
         final Teacher teacher = teacherMapper.dtoToEntity(request);
         teacher.addStudent(student);
      } else
         teacherOptional.get().addStudent(student);

      studentRepository.save(student);

      log.info("New student saved {}", request);
      return "Student saved successfully";
   }

   @Override
   public String saveStudent(StudentRequest request) {
      log.info("Starting to save a new student");
      if (request.getAge() < 16) throw new AgeLimitException();
      studentRepository.save(studentMapper.dtoToStudent(request));
      log.info("New student saved {}", request);
      return "Student saved successfully";
   }

   @Override
   public List<StudentGroupResponse> getAll() {
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
      log.info("All students are fetched");
      return responses;
   }

   @Override
   public StudentResponse getById(Long id) {
      log.info("Checking of {} id student", id);
      return studentMapper.studentToDto(studentRepository.findById(id).
              orElseThrow(() -> new NotFoundException("Not found student with such id=" + id)));
   }

   @Override
   public List<StudentResponse> getByName(String name) {
      log.info("Get students by name");
      List<StudentResponse> responses = new ArrayList<>();
      for (Student sstudent : studentRepository.getByName(name)) {
         responses.add(studentMapper.studentToDto(sstudent));
      }
      return responses;
   }

   @Override
   public String update(StudentRequest request, Long id, MultipartFile image) throws IOException {
      studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found student with such id=" + id));
      log.info("Updating {} id student", id);
      Student student = studentMapper.dtoToStudent(request);
      student.setId(id);
//        Files.copy(image.getInputStream(), this.path1.resolve(Objects.requireNonNull(image.getOriginalFilename())));
      studentRepository.save(student);
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

   public Resource getStudentImage(Long id) {
      Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found student with such id=" + id));
      return getFile(student.getStudentInfo().getImage()).getBody();
   }

   public ResponseEntity<Resource> getFile(String fileName) {
      Resource resource = fileUtil.load(fileName, this.path);
      return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(resource);
   }

}