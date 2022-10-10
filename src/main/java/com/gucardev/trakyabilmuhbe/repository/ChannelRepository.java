package com.gucardev.trakyabilmuhbe.repository;

import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query(nativeQuery = true, value = "SELECT * from channel c WHERE c.user_id=:user_id OR c.can_send_others = true")
    List<Channel> findAllChannelsByUserOrCanSendOthers(Long user_id);

}
