

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
