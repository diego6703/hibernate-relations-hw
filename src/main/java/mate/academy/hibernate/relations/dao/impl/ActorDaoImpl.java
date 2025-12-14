package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActorDaoImpl extends AbstractDao implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Actor add(Actor actor) {
        Transaction transaction = null;
        try (Session session = this.factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(actor);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not add actor: " + actor, ex);
        }
        return actor;
    }

    @Override
    public Optional<Actor> get(Long id) {
        Optional<Actor> actor = null;
        try (Session session = this.factory.openSession()) {
            actor = Optional.ofNullable(session.get(Actor.class, id));
        } catch (Exception ex) {
            throw new DataProcessingException("Could not get movie: " + actor, ex);
        }
        return actor;
    }
}
