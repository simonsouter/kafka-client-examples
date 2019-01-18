

<a name="subscribe"></a>
### Subscribe
Subscription request


|Name|Description|Schema|v1|v2|
|---|---|---|---|---|
|**sku**  <br>*required*| |< string > array|x|x|
|**deviceId**  <br>*optional*|The device which is associated with the subscription|string|x|x|
|**accountId**  <br>*optional*|The account which is associated with the subscription|string|x|x|
|**type**  <br>*required*| |enum (BAM_IDENTITY, BEARER, EXTERNAL_IDENTITY)|x|x|
|**externalIdentity**  <br>*optional*|Specific field only valid for external identity type used for grouping multiple purchases based on the externalIdentity|string|x| |
|**source**  <br>*required*|Source information associated with subscription|[SubscriptionSource](#subscriptionsource)|x|x|
|**status**  <br>*required*| |[status](#subscribe-status)|x|x|
|**cancellation**  <br>*required*| |[cancellation](#cancellation)||x|
|**offer**  <br>*required*| |[offer](#offer)||x|
|**startDate**  <br>*optional*|The date in which the subscription is started. It's optional because we may use<br>the same model for update which we don't need to update the start date. When this<br>field is empty and the operation leads to an update, the start date would be untouched.|string (date-time)|x|x|
|**expirationDate**  <br>*optional*|The date which the subscription is valid until. When expiration date is empty<br>it means that the subscription would be valid until an external system (like Poller)<br>notify this service to update the subscription state.|string (date-time)|x| |
|**revokeAsOf**  <br>*optional*|The date which the subscription is valid until. When revokeAsOf date is empty<br>it means that the subscription would be valid until an external system (like Poller)<br>notify this service to update the subscription state.|string (date-time)| |x|
|**churnDate**  <br>*optional*|The date which the subscription is is considered churned (entitlements revoked).|string (date-time)| |x|
|**expiryType**  <br>*required*|The expiry and start dates are optional this field indicates why<br>the type of expiry. Type Expiry requires expiration_date to be set.|enum (EXPIRY, UNKNOWN, NO_EXPIRY)|x| |
|**lastSyncDate**  <br>*optional*| |string (date-time)|x|x|
|**nextRenewalDate**  <br>*optional*| |string (date-time)|x|x|


<a name="subscriptionsource"></a>
### SubscriptionSource
Proof of ownership


|Name|Description|Schema|
|---|---|---|
|**provider**  <br>*required*|The provider of the subscription - such as company owning the store. It is not validated by the subscription service.<br>It is part of the unique identifier of a subscription. Enumeration is used but describes just subset of the possible values. This will grow<br>as new stores are added.|enum (BAMTECH, APPLE, GOOGLE, ROKU, SAMSUNG)|
|**ref**  <br>*required*|Source reference identifier - value depends on the source type and source subType. In case of D2C it is an order id.|string|
|**subType**  <br>*optional*|The subType is a free field to provide more context about how the subscription has been acquired.<br>The subType is not validated by the subscription service and it depends on the source of the subscription. Enumeration is used but describes currently available values which are provided by the actual sources.|enum (GIFT_SUB, GIFT_CARD, PAID, PROMO_REDEMPTION, COMP, TEMP)|
|**type**  <br>*required*|The original source of the subscriptions used as part of the<br>primary key, D2C and CST are commerce specific and IAP, MVPD are activation specific. It is defined as enum but it is not validated as new sources can be added.|enum (D2C, MVPD, IAP, CST)|


<a name="subscribe-status"></a>
**status**

|Name|Schema|
|---|---|
|**subType**  <br>*optional*|string|
|**type**  <br>*required*|enum (SUBSCRIBED, UNSUBSCRIBED)|

<a name="cancellation"></a>
**cancellation**

|Name|Schema|
|---|---|
|**reason**  <br>*required*|enum (VOLUNTARY_CANCEL, INVOLUNTARY_CANCEL, TRIAL_ABANDONED, UNKNOWN_CANCEL)|
|**status**  <br>*optional*|string|

<a name="offer"></a>
**offer**

|Name|Schema|
|---|---|
|**type**  <br>*required*|enum (FREE_TRIAL, DISCOUNT, PROMO_REDEMPTION)|
|**endDate**  <br>*optional*|string (date-time)|

