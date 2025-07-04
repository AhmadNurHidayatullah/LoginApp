/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginApp.LoginApp;

import LoginApp.LoginApp.exceptions.NonexistentEntityException;
import LoginApp.LoginApp.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Acer
 */
public class DataUserJpaController implements Serializable {

    public DataUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoginApp_LoginApp_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataUser dataUser) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDataUser(dataUser.getIdUser()) != null) {
                throw new PreexistingEntityException("DataUser " + dataUser + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataUser dataUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataUser = em.merge(dataUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dataUser.getIdUser();
                if (findDataUser(id) == null) {
                    throw new NonexistentEntityException("The dataUser with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DataUser dataUser;
            try {
                dataUser = em.getReference(DataUser.class, id);
                dataUser.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataUser with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DataUser> findDataUserEntities() {
        return findDataUserEntities(true, -1, -1);
    }

    public List<DataUser> findDataUserEntities(int maxResults, int firstResult) {
        return findDataUserEntities(false, maxResults, firstResult);
    }

    private List<DataUser> findDataUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DataUser.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DataUser findDataUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getDataUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DataUser> rt = cq.from(DataUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
