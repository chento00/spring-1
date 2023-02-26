package com.example.spring1.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }
    public void addNewStuent(@RequestBody Student student){
        Optional<Student> studentOptional=studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email token");
        }studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
       Boolean b= studentRepository.existsById(studentId);
       if(!b){
           throw  new IllegalStateException(
                   "student with id "+studentId+" does not exit"
           );
       }
       studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId,String name,String email){
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new IllegalStateException(
                        "student with id "+studentId+" does not exit"
                )
        );
        if(name != null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if (email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
