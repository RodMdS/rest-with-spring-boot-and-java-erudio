package br.com.rodmds.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Book;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> booksVO = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            booksVO.add(mockVO(i));
        }
        return booksVO;
    }

    public Book mockEntity(Integer number) {
        Book bookEntity = new Book();
        bookEntity.setId(number.longValue());
        bookEntity.setTitle("Title of book " + number);
        bookEntity.setPrice(number * 10.00);
        bookEntity.setLaunchDate(new Date());
        bookEntity.setAuthor("Author Name of book " + number);
        return bookEntity;
    }

    public BookVO mockVO(Integer number) {
        BookVO bookVO = new BookVO();
        bookVO.setKey(number.longValue());
        bookVO.setTitle("Title of book " + number);
        bookVO.setPrice(number * 10.00);
        bookVO.setLaunchDate(new Date());
        bookVO.setAuthor("Author Name of book " + number);
        return bookVO;
    }

}
