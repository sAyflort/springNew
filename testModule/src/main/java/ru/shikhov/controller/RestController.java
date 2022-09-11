package ru.shikhov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shikhov.exceptions.EntityNotFoundException;
import ru.shikhov.model.dto.ProductDto;
import ru.shikhov.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/*@RequestMapping("/api/v1/students")
@RestController
public class StudentsRestController {
private StudentsService studentsService;
@Autowired
public void setStudentsService(StudentsService studentsService) {
this.studentsService = studentsService;
}
@GetMapping("/{id}")
public Student getStudentById(@PathVariable Long id) {
return studentsService.getStudentById(id);
}
@GetMapping
public List<Student> getAllStudents() {
return studentsService.getAllStudentsList();
}
© geekbrains.ru 6
@PostMapping
public Student addStudent(@RequestBody Student student) {
student.setId(0L);
return studentsService.saveOrUpdate(student);
}
@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
public Student updateStudent(@RequestBody Student student) {
return studentsService.saveOrUpdate(student);
}
@DeleteMapping("/{id}")
public int deleteStudent(@PathVariable Long id) {
studentsService.delete(id);
return HttpStatus.OK.value();
}
@ExceptionHandler
public ResponseEntity<StudentsErrorResponse>
handleException(StudentNotFoundException exc) {
StudentsErrorResponse studentsErrorResponse = new
StudentsErrorResponse();
studentsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
studentsErrorResponse.setMessage(exc.getMessage());
studentsErrorResponse.setTimestamp(System.currentTimeMillis());
return new ResponseEntity<>(studentsErrorResponse,
HttpStatus.NOT_FOUND);
}
}
*/

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class RestController {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> listPage(
            @RequestParam(required = false) Double minPriceFilter,
            @RequestParam(required = false) Double maxPriceFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size
    ) {
        Integer pageValue = page.orElse(1)-1;
        Integer sizeValue = size.orElse(10);
        return service.productByFilter(minPriceFilter, maxPriceFilter, pageValue, sizeValue).getContent();
    }


    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id) {
        return service.findProductById(id).orElse(new ProductDto());
    }


    @DeleteMapping("/{id}")
    public int deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        return HttpStatus.OK.value();
    }

    @PutMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        service.save(product);
        return product;
    }

}
