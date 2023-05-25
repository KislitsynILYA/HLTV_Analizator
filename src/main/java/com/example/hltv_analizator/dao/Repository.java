package com.example.hltv_analizator.dao;

import com.example.hltv_analizator.entity.AdrPlayer;
import com.example.hltv_analizator.entity.DprPlayer;
import com.example.hltv_analizator.entity.ImpactPlayer;
import com.example.hltv_analizator.entity.KastPlayer;
import com.example.hltv_analizator.entity.KprPlayer;
import com.example.hltv_analizator.entity.Parameter;
import com.example.hltv_analizator.entity.Player;
import com.example.hltv_analizator.entity.PlayerAchievement;
import com.example.hltv_analizator.entity.PlayoffRatingPlayer;
import com.example.hltv_analizator.entity.PointsAchievement;
import com.example.hltv_analizator.entity.RatingPlayer;
import com.example.hltv_analizator.entity.Result;
import com.example.hltv_analizator.entity.TierTournament;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Repository
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Transactional
public class Repository {

    @Autowired
    private EntityManager entityManager;


    public List<Integer> getAllHltvId() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.hltv_id FROM Player p", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Integer> getAllPlayer_ids() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.player_id.player_id FROM RatingPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Integer> getAllPlayerPlayoff_ids() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.player_id.player_id FROM PlayoffRatingPlayer p " +
                    "ORDER BY p.player_id.player_id", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Integer> getAllPlayerAchievement_ids() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.player_id.player_id FROM PlayerAchievement p " +
                    "ORDER BY p.player_id.player_id", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Integer> getAllResult_ids() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.player_id.player_id FROM Result p " +
                    "ORDER BY p.player_id.player_id", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public RatingPlayer getByRatingPlayer_Id_tier1(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM RatingPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 1", RatingPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public RatingPlayer getByRatingPlayer_Id_tier2(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM RatingPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 2", RatingPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public PlayoffRatingPlayer getByPlayoffRatingPlayer_Id(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM PlayoffRatingPlayer rp JOIN rp.player_id p " +
                                    "WHERE p.player_id = :id_player", PlayoffRatingPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Result getByResultPlayer_Id(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM Result rp JOIN rp.player_id p " +
                                    "WHERE p.player_id = :id_player", Result.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public PlayerAchievement getByPlayerAchievement_Id(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM PlayerAchievement rp JOIN rp.player_id p " +
                                    "WHERE p.player_id = :id_player", PlayerAchievement.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public AdrPlayer getByAdrPlayer_Id_tier1(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM AdrPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 1", AdrPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public AdrPlayer getByAdrPlayer_Id_tier2(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM AdrPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 2", AdrPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public DprPlayer getByDprPlayer_Id_tier1(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM DprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 1", DprPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public DprPlayer getByDprPlayer_Id_tier2(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM DprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 2", DprPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public ImpactPlayer getByImpactPlayer_Id_tier1(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM ImpactPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 1", ImpactPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ImpactPlayer getByImpactPlayer_Id_tier2(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM ImpactPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 2", ImpactPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KastPlayer getByKastPlayer_Id_tier1(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KastPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 1", KastPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KastPlayer getByKastPlayer_Id_tier2(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KastPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 2", KastPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KprPlayer getByKprPlayer_Id_tier1(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 1", KprPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KprPlayer getByKprPlayer_Id_tier2(Integer id_player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.player_id = :id_player AND t.tier_id = 2", KprPlayer.class)
                    .setParameter("id_player", id_player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Double> getAllRating() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Double> query = session.createQuery("SELECT p.rating FROM RatingPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Double.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Double> getAllPlayoffRating() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Double> query = session.createQuery("SELECT p.rating_playoff FROM PlayoffRatingPlayer p " +
                    "ORDER BY p.player_id.player_id", Double.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Double> getAllAdr() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Double> query = session.createQuery("SELECT p.adr FROM AdrPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Double.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Double> getAllDpr() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Double> query = session.createQuery("SELECT p.dpr FROM DprPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Double.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Double> getAllImpact() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Double> query = session.createQuery("SELECT p.impact FROM ImpactPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Double.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Double> getAllKast() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Double> query = session.createQuery("SELECT p.kast FROM KastPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Double.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Double> getAllKpr() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Double> query = session.createQuery("SELECT p.kpr FROM KprPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Double.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Integer> getAllMaps_numb() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.maps_numb FROM RatingPlayer p " +
                    "ORDER BY p.player_id.player_id, p.tier_id.tier_id", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Integer> getAllMaps_numb_Playoff() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.maps_numb FROM PlayoffRatingPlayer p " +
                    "ORDER BY p.player_id.player_id", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Parameter> getParams(short id_cat, short id_tier) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Parameter> query = session.createQuery("SELECT p " +
                    "FROM Parameter p " +
                    "WHERE p.category_id = :catId AND p.tier_id.tier_id = :tierId", Parameter.class);
            query.setParameter("catId", id_cat);
            query.setParameter("tierId", id_tier);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<PlayerAchievement> getNumbAchievements() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<PlayerAchievement> query = session.createQuery("SELECT p " +
                    "FROM PlayerAchievement p " +
                    "ORDER BY p.player_id.player_id", PlayerAchievement.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Result> getTop20Results() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Result> query = session.createQuery("SELECT p " +
                    "FROM Result p " +
                    "ORDER BY p.full_points desc", Result.class);
            query.setMaxResults(20);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public PointsAchievement getPointsAchievement() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<PointsAchievement> query = session.createQuery("SELECT p " +
                    "FROM PointsAchievement p ", PointsAchievement.class);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Integer> getAllId() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Integer> query = session.createQuery("SELECT p.player_id FROM Player p", Integer.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Short> getAllTierId() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Short> query = session.createQuery("SELECT p.tier_id FROM TierTournament p", Short.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Short> getAllCoef_Tier() {
        try (Session session = entityManager.unwrap(Session.class)) {
            Query<Short> query = session.createQuery("SELECT p.coef_tier FROM TierTournament p " +
                    "ORDER BY p.tier_id", Short.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Player getById(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery("from Player where hltv_id = :hltv_id", Player.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public RatingPlayer getByIdRatingPlayer_tier1(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM RatingPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 1", RatingPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public RatingPlayer getByIdRatingPlayer_tier2(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM RatingPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 2", RatingPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public AdrPlayer getByIdAdrPlayer_tier1(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM AdrPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 1", AdrPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public AdrPlayer getByIdAdrPlayer_tier2(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM AdrPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 2", AdrPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public DprPlayer getByIdDprPlayer_tier1(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM DprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 1", DprPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public DprPlayer getByIdDprPlayer_tier2(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM DprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 2", DprPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public ImpactPlayer getByIdImpactPlayer_tier1(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM ImpactPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 1", ImpactPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ImpactPlayer getByIdImpactPlayer_tier2(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM ImpactPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 2", ImpactPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KastPlayer getByIdKastPlayer_tier1(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KastPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 1", KastPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KastPlayer getByIdKastPlayer_tier2(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KastPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 2", KastPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KprPlayer getByIdKprPlayer_tier1(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 1", KprPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public KprPlayer getByIdKprPlayer_tier2(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM KprPlayer rp JOIN rp.player_id p JOIN rp.tier_id t " +
                                    "WHERE p.hltv_id = :hltv_id AND t.tier_id = 2", KprPlayer.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Result getByIdResults(Integer hltv_id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery(
                            "SELECT rp FROM Result rp JOIN rp.player_id p " +
                                    "WHERE p.hltv_id = :hltv_id", Result.class)
                    .setParameter("hltv_id", hltv_id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void savePlayer(Player player) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(player);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void saveRatingPlayer(RatingPlayer ratingPlayer) {
        try (Session session = entityManager.unwrap(Session.class)) {
                session.merge(ratingPlayer);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void saveResult(Result result) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(result);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void savePlayoffRatingPlayer(PlayoffRatingPlayer playoffRatingPlayer) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(playoffRatingPlayer);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerAchievement(PlayerAchievement playerAchievement) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(playerAchievement);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void saveAdrPlayer(AdrPlayer adrPlayer) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(adrPlayer);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void saveDprPlayer(DprPlayer dprPlayer) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(dprPlayer);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }
    public void saveImpactPlayer(ImpactPlayer impactPlayer) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(impactPlayer);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void saveKastPlayer(KastPlayer kastPlayer) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(kastPlayer);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void saveKprPlayer(KprPlayer kprPlayer) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.merge(kprPlayer);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    public void updateRatingPlayer_tier1() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                RatingPlayer ratingPlayer = new RatingPlayer();
                ratingPlayer.setPlayer_id(player);
                ratingPlayer.setTier_id(tierList.get(0));
                session.persist(ratingPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }

    public void updateRatingPlayer_tier2() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                RatingPlayer ratingPlayer = new RatingPlayer();
                ratingPlayer.setPlayer_id(player);
                ratingPlayer.setTier_id(tierList.get(1));
                session.persist(ratingPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }

    public void updatePlayoffRatingPlayer() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            for (Player player : playerList) {
                PlayoffRatingPlayer playoffRatingPlayer = new PlayoffRatingPlayer();
                playoffRatingPlayer.setPlayer_id(player);
                session.persist(playoffRatingPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }

    public void updateResult() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            for (Player player : playerList) {
                Result result = new Result();
                result.setPlayer_id(player);
                session.persist(result);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }

    public void updatePlayerAchievement() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            for (Player player : playerList) {
                PlayerAchievement playerAchievement = new PlayerAchievement();
                playerAchievement.setPlayer_id(player);
                session.persist(playerAchievement);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }

    public void updateAdrPlayer_tier1() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                AdrPlayer adrPlayer = new AdrPlayer();
                adrPlayer.setPlayer_id(player);
                adrPlayer.setTier_id(tierList.get(0));
                session.persist(adrPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public void updateAdrPlayer_tier2() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                AdrPlayer adrPlayer = new AdrPlayer();
                adrPlayer.setPlayer_id(player);
                adrPlayer.setTier_id(tierList.get(1));
                session.persist(adrPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public void updateDprPlayer_tier1() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                DprPlayer dprPlayer = new DprPlayer();
                dprPlayer.setPlayer_id(player);
                dprPlayer.setTier_id(tierList.get(0));
                session.persist(dprPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public void updateDprPlayer_tier2() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                DprPlayer dprPlayer = new DprPlayer();
                dprPlayer.setPlayer_id(player);
                dprPlayer.setTier_id(tierList.get(1));
                session.persist(dprPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public void updateImpactPlayer_tier1() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                ImpactPlayer impactPlayer = new ImpactPlayer();
                impactPlayer.setPlayer_id(player);
                impactPlayer.setTier_id(tierList.get(0));
                session.persist(impactPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public void updateImpactPlayer_tier2() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                ImpactPlayer impactPlayer = new ImpactPlayer();
                impactPlayer.setPlayer_id(player);
                impactPlayer.setTier_id(tierList.get(1));
                session.persist(impactPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }

    public void updateKastPlayer_tier1() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                KastPlayer kastPlayer = new KastPlayer();
                kastPlayer.setPlayer_id(player);
                kastPlayer.setTier_id(tierList.get(0));
                session.persist(kastPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public void updateKastPlayer_tier2() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                KastPlayer kastPlayer = new KastPlayer();
                kastPlayer.setPlayer_id(player);
                kastPlayer.setTier_id(tierList.get(1));
                session.persist(kastPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }

    public void updateKprPlayer_tier1() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                KprPlayer kprPlayer = new KprPlayer();
                kprPlayer.setPlayer_id(player);
                kprPlayer.setTier_id(tierList.get(0));
                session.persist(kprPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public void updateKprPlayer_tier2() {
        try (Session session = entityManager.unwrap(Session.class)) {
            List<Player> playerList = session.createQuery("FROM Player", Player.class)
                    .getResultList();
            List<TierTournament> tierList = session.createQuery("FROM TierTournament", TierTournament.class)
                    .getResultList();
            for (Player player : playerList) {
                KprPlayer kprPlayer = new KprPlayer();
                kprPlayer.setPlayer_id(player);
                kprPlayer.setTier_id(tierList.get(1));
                session.persist(kprPlayer);
            }
        } catch (NoResultException e) {
            // Обработка ошибок
        }
    }
    public boolean ratingPlayerIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(RatingPlayer.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean playoffRatingPlayerIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(PlayoffRatingPlayer.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean playerAchievementIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(PlayerAchievement.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean adrPlayerIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(AdrPlayer.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean dprPlayerIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(DprPlayer.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean impactPlayerIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(ImpactPlayer.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean kastPlayerIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(KastPlayer.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean kprPlayerIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(KprPlayer.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean resultIsEmpty(){
        try (Session session = entityManager.unwrap(Session.class)) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(Result.class)));
            Long count = session.createQuery(query).uniqueResult();

            if(count == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
