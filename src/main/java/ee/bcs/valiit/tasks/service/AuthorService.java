package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.repository3.Author;
import ee.bcs.valiit.tasks.repository3.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Author getById(int id){
        return authorRepository. getOne(id);
    }
}
