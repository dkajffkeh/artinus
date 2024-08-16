package com.artinus.userapp.domain.entity.channel;

import com.artinus.userapp.constant.ChannelType;
import com.artinus.userapp.domain.entity.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHANNEL")
public class Channel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CHANNEL_NAME", length = 30, nullable = false)
    private String channelName;

    @Column(name = "CHANNEL_CODE", length = 15, nullable = false)
    private String channelCode;

    @Column(name = "CHANNEL_TYPE", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    public Channel() {

    }

    public Channel(Long id, String channelName, String channelCode,
            ChannelType channelType) {
        this.id = id;
        this.channelName = channelName;
        this.channelCode = channelCode;
        this.channelType = channelType;
    }
}
