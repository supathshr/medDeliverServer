package persistence.jobs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is used by Hibernate to map to the Jobs table.
 * <p>
 * A {@code Job} represents a job for a Med-Deliver driver.
 * <p>
 * Jobs are persistable however changes are not automatically updated to the database.
 * To persist a {@code Job} you must use the {@link hibernate.HibernateUtil#save(Object)}
 * method.
 *
 * @author Suraj Kumar <a href="mailto:sk551@kent.ac.uk">sk551@kent.ac.uk</a>
 */
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "orderId")
    private long orderId;

    @Column(name = "completed")
    private boolean completed;

    public Job() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
