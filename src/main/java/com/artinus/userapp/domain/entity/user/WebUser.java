package com.artinus.userapp.domain.entity.user;

import com.artinus.userapp.domain.entity.base.BaseEntity;
import com.artinus.userapp.domain.entity.subscription.SubscriptionActionHist;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WEB_USER")
public class WebUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webUser")
    private List<SubscriptionMst> subscriptions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webUser")
    private List<SubscriptionActionHist> actionHists = new ArrayList<>();

    public WebUser() {

    }

    public WebUser(String phoneNumber) {
        this(null, phoneNumber);
    }

    public WebUser(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public void subscribe(SubscriptionMst subscriptionMst) {
        this.subscriptions.add(subscriptionMst);
    }


}
