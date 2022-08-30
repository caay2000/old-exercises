# tdd-5-test-refactor

## New features to add

### Internal Events should not be sent to the notifier
An event is internal when the visitNumber is null or empty.

### External Engineers should send the engineerType as "external" to the notifier
An external engineer is an engineer where its engineerId starts with "EX-".
When the engineer is not external, engineerType should not be sent (actual behaviour).

### We should now send event depending on organisation
We'll need to integrate with NOWTV and OTT. 
We'll need to send the event depending on the organisation to SKY, NOWTV por OTT notifier (only one), following all the existing rules.