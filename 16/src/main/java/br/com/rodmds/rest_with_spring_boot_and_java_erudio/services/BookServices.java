package br.com.rodmds.rest_with_spring_boot_and_java_erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.controllers.BookController;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.exceptions.RequiredObjectIsNullException;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Book;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.repositories.BookRepository;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {
        logger.info("Finding all books...");
        
        var entityList = repository.findAll();
        var objectsVOList = DozerMapper.parseListObjects(entityList, BookVO.class);
        objectsVOList.stream()
                     .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return objectsVOList;
    }
    
    public BookVO findById(Long id) {
        logger.info("Finding some book...");

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for " + id));
        BookVO objectVO = DozerMapper.parseObject(entity, BookVO.class);
        objectVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return objectVO;
    }

    public BookVO createBook(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        
        logger.info("Creating a book...");

        Book entity = DozerMapper.parseObject(book, Book.class);
        BookVO objectVO = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        objectVO.add(linkTo(methodOn(BookController.class).findById(objectVO.getKey())).withSelfRel());
        return objectVO;
    }

    public BookVO updateBook(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating this book...");

        Book entity = repository.findById(
            book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for " + book.getKey())
        );
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setAuthor(book.getAuthor());
        BookVO objectVO = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        objectVO.add(linkTo(methodOn(BookController.class).findById(objectVO.getKey())).withSelfRel());
        return objectVO;
    }

    public void deleteBook(Long id) {
        logger.info("Deleting this book...");

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for " + id));
        repository.delete(entity);
    }

}
