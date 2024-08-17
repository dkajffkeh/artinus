package com.artinus.userapp.domain.entity.channel;

import com.artinus.userapp.constant.ChannelType;
import com.artinus.userapp.domain.entity.base.BaseEntity;
import java.util.Objects;
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

    @Column(name = "CHANNEL_TYPE", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    public Channel() {

    }

    public Channel(String channelName, ChannelType channelType) {
        this(null, channelName, channelType);
    }



    public Channel(Long id, String channelName,
            ChannelType channelType) {
        this.id = id;
        this.channelName = channelName;
        this.channelType = channelType;
    }

    public Long getId() {
        return id;
    }

    public String getChannelName() {
        return channelName;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public boolean isCancelOnly() {
        return this.channelType == ChannelType.CANCELLATION_ONLY;
    }

    public boolean isSubscribeOnly() {
        return this.channelType == ChannelType.SUBSCRIPTION_ONLY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
