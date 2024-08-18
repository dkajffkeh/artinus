## 실행 방법 🚀

언어 : `JAVA 17`

프레임 워크 : `Spring Boot 2.7.13`

Database : `H2 - MySQL Mode`

DB 구조: [`ERD`](./docs/requirements/DB설계.md)

요구사항 분석: [`요구사항 분석`](./docs/requirements/요구사항분석.md)

기동 방법 
```text
${project}/build.sh ${PORT_NUMBER}

ex) .../build.sh 8080
```


### Test 전화번호

- `phoneNumber` : "01012341234"

### API 정의

- API 공통 Response

| Element | Data Type | Parent | Description           |
|---------|-----------|--------|-----------------------|
| meta    | Object    |        | 응답 메타데이터              |
| code    | int       |        | 응답 코드 HttpStatus Base |
| message | Stirng    |        | API Message           |
| data    | Object    |        | 응답 Body               |

***Response Sample***

```json
{
  "meta": {
    "code": 200,
    "message": "ok"
  },
  "data": {
    "id": 1,
    "name": "test"
  }
}
```

### 1. 구독하기 API

- `Protocol` : HTTP
- `Method` : POST
- `URI` - /api/subscription

| Element     | Parameter Type | Data Type | Parent | Description                                                       | Mandatory |
|-------------|----------------|-----------|--------|-------------------------------------------------------------------|-----------|
| phoneNumber | body           | String    |        | 휴대폰 번호                                                            | O         |
| channelId   | body           | Long      |        | 채널 아이디                                                            | O         |
| subscriptionStatus   | body           | String    |        | 구독 상태 (NOT_SUBSCRIBED, REGULAR_SUBSCRIPTION, PREMIUM_SUBSCRIPTION) | O         |

`Request Parameters`

```json
{
  "phoneNumber": "01012341234",
  "channelId": 1,
  "subscriptionStatus": "REGULAR_SUBSCRITION"
}
```

`Response Parameters and Body`

```json
{
  "meta": {
    "code": 200,
    "message": "ok"
  },
  "data": null
}
```

### 2. 구독해지 API

- `Protocol` : HTTP
- `Method` : PATCH
- `URI` - /api/subscription

| Element     | Parameter Type | Data Type | Parent | Description                                                       | Mandatory |
|-------------|----------------|-----------|--------|-------------------------------------------------------------------|-----------|
| phoneNumber | body           | String    |        | 휴대폰 번호                                                            | O         |
| channelId   | body           | Long      |        | 채널 아이디                                                            | O         |
| subscriptionStatus   | body           | String    |        | 구독 상태 (NOT_SUBSCRIBED, REGULAR_SUBSCRIPTION, PREMIUM_SUBSCRIPTION) | O         |

`Request Parameters`

```json
{
  "phoneNumber": "01012341234",
  "channelId": 1,
  "subscriptionStatus": "REGULAR_SUBSCRIPTION"
}
```

`Response Parameters and Body`

```json
{
  "meta": {
    "code": 200,
    "message": "ok"
  },
  "data": null
}
```

### 3. 구독 이력 조회 API

- `Protocol` : HTTP
- `Method` : GET
- `URI` - /api/subscriptions

| Element     | Parameter Type | Data Type | Parent | Description                                                       | Mandatory |
|-------------|----------------|-----------|--------|-------------------------------------------------------------------|-----------|
| phoneNumber | Query          | String    |        | 휴대폰 번호                                                            | O         |

`Response Parameters and Body`

| Element      | Data Type | Parent       | Description  |
|--------------|-----------|--------------|--------------|
| hists        | Array     |              |              |
| date         | String    | hists        | 날짜           |
| channelHists | Array     | hists        | 채널 이력        |
| channelName  | String    | channelHists | 채널명          |
| channelId    | Long      | channelHists | 채널 아이디       |
| actions    | Array     | channelHists      | 구독 이벤트 발생 이력 |
| subscriptionStart    | String    | actions      | 구독하기 발생일     |
| subscriptionEnd    | String    | actions      | 구독 취소 발생일    |
| prevSubscriptionType    | String    | actions      | 구독이전 상태      |
| nextSubscriptionType    | String    | actions      | 구독이후 상태      |
| actionDateTime    | DateTime  | actions      | 구독 이벤트 상세 시간 |

```json
{
  "meta": {
    "code": 200,
    "message": "ok"
  },
  "data": {
    "hists": [
      {
        "date": "20240327",
        "channelHists": [
          {
            "channelName": "쿠팡",
            "channelId": 1,
            "actions": [
              {
                "subscriptionStart": "20240201",
                "subscriptionEnd": "20240327",
                "prevSubscriptionType": "PREMIUM_SUBSCRIPTION",
                "nextSubscriptionType": "REGULAR_SUBSCRIPTION",
                "actionDateTime": "2024-03-27T14:18:18Z"
              }
            ]
          }
        ]
      },
      {
        "date": "20240326",
        "channelHists": [
          {
            "channelName": "티몬",
            "channelId": 1,
            "actions": [
              {
                "subscriptionStart": "20240326",
                "subscriptionEnd": "",
                "prevSubscriptionType": "REGULAR_SUBSCRIPTION",
                "nextSubscriptionType": "PREMIUM_SUBSCRIPTION",
                "actionDateTime": "2024-03-26T14:18:18Z"
              }
            ]
          }
        ]
      }
    ]
  }
}
```

### 4. 채널 이력조회 API

- `Protocol` : HTTP
- `Method` : GET
- `URI` - /api/channels

| Element   | Parameter Type | Data Type | Parent | Description | Mandatory |
|-----------|----------------|-----------|--------|-------------|-----------|
| date      | Query          | String    |        | 날짜 yyyyMMdd | O         |
| channelId | Query          | Long      |        | 채널 아이디      | O         |

`Response Parameters and Body`

| Element              | Data Type | Parent  | Description  |
|----------------------|-----------|---------|--------------|
| date                 | String    |         | 날짜           |
| channelName          | String    |         | 채널명          |
| channelId            | Long      |         | 채널 아이디       |
| hists                | Array     |         |              |
| userId               | Long      | hists   |              |
| phoneNumber          | String    | hists   |              |
| actions              | Array     | hists   | 구독 이벤트 발생 이력 |
| subscriptionStart    | String    | actions | 구독하기 발생일     |
| subscriptionEnd      | String    | actions | 구독 취소 발생일    |
| prevSubscriptionType | String    | actions | 구독이전 상태      |
| nextSubscriptionType | String    | actions | 구독이후 상태      |
| actionDateTime       | DateTime  | actions | 구독 이벤트 상세 시간 |

```json
{
  "meta": {
    "code": 200,
    "message": "ok"
  },
  "data": {
    "date": "20240326",
    "channelId": 1,
    "channelName": "쿠팡",
    "hists": [
      {
        "userId": 1,
        "phoneNumber": "01012341234",
        "actions": [
          {
            "subscriptionStart": "20240326",
            "subscriptionEnd": "",
            "prevSubscriptionType": "REGULAR_SUBSCRIPTION",
            "nextSubscriptionType": "PREMIUM_SUBSCRIPTION",
            "actionDateTime": "2024-03-26T14:18:18Z"
          }
        ]
      },
      {
        "userId": 2,
        "phoneNumber": "01012341235",
        "actions": [
          {
            "subscriptionStart": "20240326",
            "subscriptionEnd": "",
            "prevSubscriptionType": "REGULAR_SUBSCRIPTION",
            "nextSubscriptionType": "PREMIUM_SUBSCRIPTION",
            "actionDateTime": "2024-03-26T14:18:21Z"
          }
        ]
      }
    ]
  }
}
```
