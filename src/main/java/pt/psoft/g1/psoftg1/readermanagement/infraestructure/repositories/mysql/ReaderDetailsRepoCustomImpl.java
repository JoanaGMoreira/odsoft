package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.mysql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.ReaderDetailsMapper;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.readermanagement.repositories.ReaderRepository;
import pt.psoft.g1.psoftg1.readermanagement.services.ReaderBookCountDTO;
import pt.psoft.g1.psoftg1.readermanagement.services.SearchReadersQuery;
import pt.psoft.g1.psoftg1.usermanagement.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
class ReaderDetailsRepoCustomImpl implements ReaderRepository {

    private final EntityManager em;
    private final MysqlReaderRepository mysqlReaderRepository;


    @Override
    public Optional<ReaderDetails> findByReaderNumber(String readerNumber) {
        return mysqlReaderRepository.findByReaderNumber(readerNumber).map(ReaderDetailsMapper::toModel);
    }

    @Override
    public List<ReaderDetails> findByPhoneNumber(String phoneNumber) {
        return mysqlReaderRepository.findByPhoneNumber(phoneNumber).stream().map(ReaderDetailsMapper::toModel).toList();
    }

    @Override
    public Optional<ReaderDetails> findByUsername(String username) {
        return mysqlReaderRepository.findByUsername(username).map(ReaderDetailsMapper::toModel);
    }

    @Override
    public Optional<ReaderDetails> findByUserId(Long userId) {
        return mysqlReaderRepository.findByUserId(userId).map(ReaderDetailsMapper::toModel);
    }

    @Override
    public int getCountFromCurrentYear() {
        return mysqlReaderRepository.getCountFromCurrentYear();
    }

    @Override
    public ReaderDetails save(ReaderDetails readerDetails) {
        return ReaderDetailsMapper.toModel(mysqlReaderRepository.save(ReaderDetailsMapper.toEntity(readerDetails)));
    }

    @Override
    public List<ReaderDetails> findAll() {
        return mysqlReaderRepository.findAll().stream().map(ReaderDetailsMapper::toModel).toList();
    }

    @Override
    public Page<ReaderDetails> findTopReaders(Pageable pageable) {
        return mysqlReaderRepository.findTopReaders(pageable).map(ReaderDetailsMapper::toModel);
    }

    @Override
    public Page<ReaderBookCountDTO> findTopByGenre(Pageable pageable, String genre, LocalDate startDate, LocalDate endDate) {
        return mysqlReaderRepository.findTopByGenre(pageable, genre, startDate, endDate);
    }

    @Override
    public void delete(ReaderDetails readerDetails) {
        mysqlReaderRepository.delete(ReaderDetailsMapper.toEntity(readerDetails));
    }

    @Override
    public List<ReaderDetails> searchReaderDetails(final pt.psoft.g1.psoftg1.shared.services.Page page, final SearchReadersQuery query) {

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ReaderDetails> cq = cb.createQuery(ReaderDetails.class);
        final Root<ReaderDetails> readerDetailsRoot = cq.from(ReaderDetails.class);
        Join<ReaderDetails, User> userJoin = readerDetailsRoot.join("reader");

        cq.select(readerDetailsRoot);

        final List<Predicate> where = new ArrayList<>();
        if (StringUtils.hasText(query.getName())) { //'contains' type search
            where.add(cb.like(userJoin.get("name").get("name"), "%" + query.getName() + "%"));
            cq.orderBy(cb.asc(userJoin.get("name")));
        }
        if (StringUtils.hasText(query.getEmail())) { //'exatct' type search
            where.add(cb.equal(userJoin.get("username"), query.getEmail()));
            cq.orderBy(cb.asc(userJoin.get("username")));

        }
        if (StringUtils.hasText(query.getPhoneNumber())) { //'exatct' type search
            where.add(cb.equal(readerDetailsRoot.get("phoneNumber").get("phoneNumber"), query.getPhoneNumber()));
            cq.orderBy(cb.asc(readerDetailsRoot.get("phoneNumber").get("phoneNumber")));
        }

        // search using OR
        if (!where.isEmpty()) {
            cq.where(cb.or(where.toArray(new Predicate[0])));
        }


        final TypedQuery<ReaderDetails> q = em.createQuery(cq);
        q.setFirstResult((page.getNumber() - 1) * page.getLimit());
        q.setMaxResults(page.getLimit());

        return q.getResultList();
    }
}
