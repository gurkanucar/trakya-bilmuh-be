package com.gucardev.trakyabilmuhbe.repository;

import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByChannelOrderByCreatedDateTimeDesc(Channel channel);

}
