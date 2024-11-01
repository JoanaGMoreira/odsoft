package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.authormanagement.api.AuthorLendingView;
import pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.AuthorMapper;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MySQLAuthorRepositoryImpl implements AuthorRepository {
    private final MySQLAuthorRepository mysqlRepository;

    @Autowired
    public MySQLAuthorRepositoryImpl(@Lazy MySQLAuthorRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    @Override
    public Optional<Author> findByAuthorNumber(Long authorNumber) {
        return mysqlRepository.findByAuthorNumber(authorNumber)
                .map(AuthorMapper::toModel);
    }

    @Override
    public List<Author> searchByNameNameStartsWith(String name) {
        return List.of();
    }

    @Override
    public List<Author> searchByNameName(String name) {
        return List.of();
    }

    @Override
    public Author save(Author author) {
        return AuthorMapper.toModel(mysqlRepository.save(AuthorMapper.toEntity(author)));
    }

    @Override
    public List<Author> findAll() {
        return mysqlRepository.findAll()
                .stream()
                .map(AuthorMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuthorLendingView> findTopAuthorByLendings(Pageable pageableRules) {
        return null;
    }

    @Override
    public void delete(Author author) {
        mysqlRepository.delete(AuthorMapper.toEntity(author));
    }

    @Override
    public List<Author> findCoAuthorsByAuthorNumber(Long authorNumber) {
        return mysqlRepository.findCoAuthorsByAuthorNumber(authorNumber)
                .stream()
                .map(AuthorMapper::toModel)
                .collect(Collectors.toList());
    }
}
