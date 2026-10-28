package kz.iitu.goslinuly.studentapi;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kz.iitu.goslinuly.studentapi.model.Student;
import kz.iitu.goslinuly.studentapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository repository;

    private Student gosling;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        gosling = repository.save(new Student("Goslinuly", "Goslinuly", "gosling.goslinuly@student.iitu.kz", "Goslinuly"));
    }

    @Test
    void infoShowsStudentName() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.student").value("Goslinuly WRONG"))
                .andExpect(jsonPath("$.studentId").value("gosling"));
    }

    @Test
    void createStudent() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Aigerim\",\"lastName\":\"Test\",\"email\":\"aigerim.test@student.iitu.kz\",\"groupName\":\"Goslinuly\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Aigerim"));
    }

    @Test
    void findAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastName").value("Goslinuly"));
    }

    @Test
    void findStudentById() throws Exception {
        mockMvc.perform(get("/api/students/" + gosling.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Goslinuly"));
    }

    @Test
    void updateStudent() throws Exception {
        mockMvc.perform(put("/api/students/" + gosling.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Goslinuly\",\"lastName\":\"Goslinuly\",\"email\":\"gosling.goslinuly@student.iitu.kz\",\"groupName\":\"Goslinuly (DevOps)\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupName").value("Goslinuly (DevOps)"));
    }

    @Test
    void updateUnknownStudentReturns404() throws Exception {
        mockMvc.perform(put("/api/students/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"X\",\"lastName\":\"Y\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(delete("/api/students/" + gosling.getId()))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/students/" + gosling.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void unknownStudentReturns404() throws Exception {
        mockMvc.perform(get("/api/students/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Student with id 99999 not found"));
    }
}
