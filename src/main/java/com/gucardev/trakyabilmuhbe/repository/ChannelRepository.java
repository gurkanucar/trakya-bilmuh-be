package com.gucardev.trakyabilmuhbe.repository;

import com.gucardev.trakyabilmuhbe.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
