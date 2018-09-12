package com.test;

import java.util.List;

/**
 * Created by zhangruigang on 2017/9/23.
 */
public class NetworkIncident {

    /**
     * serialId : 604
     * transactionId : cb2dc97a-bb8e-4231-a0ae-d8e9e2041746
     * trafficId : 604
     * incidentType : 1
     * detectDateTime : 2017-09-13T18:00:08.109+08:00
     * detectTime : 18:00:08
     * incidentDateTime : 2017-09-13T18:00:51.032+08:00
     * incidentTime : 18:00:51
     * severityType : 2
     * statusType : 1
     * actionType : 2
     * policyAction : 2
     * agentAction : 2
     * isIgnored : false
     * breachContents : //file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar
     * maxMatches : 1
     * transactionSize : 798779
     * deployVersion : 0
     * detectAgentUuid : 1234-dadf-2132-ddse
     * detectAgentType : 7
     * analyzeAgentUuid : 06c79bda-cff3-4fc5-8e9d-4c546a61524b
     * analyzeAgentType : 100
     * analyzeAgentHostname : Cloud-UCWI
     * analyzeAgentVersion : 1.3.1-045-cloud
     * matchedPolicies : [{"uuid":"f2bd8084-d433-4150-843c-00276d2e1a21","name":"云应用单目标单通道策略","groupName":"默认策略组","priority":1001,"severityType":2,"matchedAction":{"uuid":"b927bcc7-9d8c-46e9-b72d-a5b9153e5e17","name":"保护"},"matches":1,"isTrickle":false,"isVisible":true,"matchedRules":[{"uuid":"f0a2338c-d266-4101-a1c9-3a979489efa4","name":"文件指纹","matchedConditions":[{"uuid":"6caba887-c71b-400e-98b5-de0658b4c115","elementType":7,"isTraditionalMatching":false,"matchedElements":[{"uuid":"20aae7c5-78cc-41fb-9b73-88fe53b18091","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"DLPencrypt.rar","locationTypesPath":"4","locationType":4,"contentType":1033,"encodeType":"UnknownEncoding","contentSize":798779,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"//file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":true,"isEncryptedFile":true,"isArchiveFile":true}]}]}]}]}]
     * histories : [{"operationType":1,"adminName":"system","historyDateTime":"2017-09-13T18:00:51.032+08:00"}]
     * channelType : 25
     * workMode : 2
     * isReleased : false
     * hasForensics : true
     * isVisible : true
     * deviceId : 016cae32-c079-586a-782a-97df568228eb
     * source : {"displayName":"asynctest1\\clouds3user50","logonName":"asynctest1\\clouds3user50","processId":0,"threadId":0}
     * destinations : [{"displayName":"第一云应用","processId":0,"threadId":0,"cloudAppName":"第一云应用","cloudAppID":"97e2e930-3516-400c-a67d-f505e06d2ab9","destinationType":1,"directionType":2,"actionType":0,"policyAction":0,"agentAction":0,"isReleased":false}]
     * attachments : [{"filename":"DLPencrypt.rar","fileSize":798779,"fileType":0}]
     */

    private int serialId;
    private String transactionId;
    private int trafficId;
    private int incidentType;
    private String detectDateTime;
    private String detectTime;
    private String incidentDateTime;
    private String incidentTime;
    private int severityType;
    private int statusType;
    private int actionType;
    private int policyAction;
    private int agentAction;
    private boolean isIgnored;
    private String breachContents;
    private int maxMatches;
    private int transactionSize;
    private int deployVersion;
    private String detectAgentUuid;
    private int detectAgentType;
    private String analyzeAgentUuid;
    private int analyzeAgentType;
    private String analyzeAgentHostname;
    private String analyzeAgentVersion;
    private int channelType;
    private int workMode;
    private boolean isReleased;
    private boolean hasForensics;
    private boolean isVisible;
    private String deviceId;
    private SourceBean source;
    private List<MatchedPoliciesBean> matchedPolicies;
    private List<HistoriesBean> histories;
    private List<DestinationsBean> destinations;
    private List<AttachmentsBean> attachments;

    public int getSerialId() {
        return serialId;
    }

    public void setSerialId(int serialId) {
        this.serialId = serialId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getTrafficId() {
        return trafficId;
    }

    public void setTrafficId(int trafficId) {
        this.trafficId = trafficId;
    }

    public int getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(int incidentType) {
        this.incidentType = incidentType;
    }

    public String getDetectDateTime() {
        return detectDateTime;
    }

    public void setDetectDateTime(String detectDateTime) {
        this.detectDateTime = detectDateTime;
    }

    public String getDetectTime() {
        return detectTime;
    }

    public void setDetectTime(String detectTime) {
        this.detectTime = detectTime;
    }

    public String getIncidentDateTime() {
        return incidentDateTime;
    }

    public void setIncidentDateTime(String incidentDateTime) {
        this.incidentDateTime = incidentDateTime;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }

    public int getSeverityType() {
        return severityType;
    }

    public void setSeverityType(int severityType) {
        this.severityType = severityType;
    }

    public int getStatusType() {
        return statusType;
    }

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getPolicyAction() {
        return policyAction;
    }

    public void setPolicyAction(int policyAction) {
        this.policyAction = policyAction;
    }

    public int getAgentAction() {
        return agentAction;
    }

    public void setAgentAction(int agentAction) {
        this.agentAction = agentAction;
    }

    public boolean isIsIgnored() {
        return isIgnored;
    }

    public void setIsIgnored(boolean isIgnored) {
        this.isIgnored = isIgnored;
    }

    public String getBreachContents() {
        return breachContents;
    }

    public void setBreachContents(String breachContents) {
        this.breachContents = breachContents;
    }

    public int getMaxMatches() {
        return maxMatches;
    }

    public void setMaxMatches(int maxMatches) {
        this.maxMatches = maxMatches;
    }

    public int getTransactionSize() {
        return transactionSize;
    }

    public void setTransactionSize(int transactionSize) {
        this.transactionSize = transactionSize;
    }

    public int getDeployVersion() {
        return deployVersion;
    }

    public void setDeployVersion(int deployVersion) {
        this.deployVersion = deployVersion;
    }

    public String getDetectAgentUuid() {
        return detectAgentUuid;
    }

    public void setDetectAgentUuid(String detectAgentUuid) {
        this.detectAgentUuid = detectAgentUuid;
    }

    public int getDetectAgentType() {
        return detectAgentType;
    }

    public void setDetectAgentType(int detectAgentType) {
        this.detectAgentType = detectAgentType;
    }

    public String getAnalyzeAgentUuid() {
        return analyzeAgentUuid;
    }

    public void setAnalyzeAgentUuid(String analyzeAgentUuid) {
        this.analyzeAgentUuid = analyzeAgentUuid;
    }

    public int getAnalyzeAgentType() {
        return analyzeAgentType;
    }

    public void setAnalyzeAgentType(int analyzeAgentType) {
        this.analyzeAgentType = analyzeAgentType;
    }

    public String getAnalyzeAgentHostname() {
        return analyzeAgentHostname;
    }

    public void setAnalyzeAgentHostname(String analyzeAgentHostname) {
        this.analyzeAgentHostname = analyzeAgentHostname;
    }

    public String getAnalyzeAgentVersion() {
        return analyzeAgentVersion;
    }

    public void setAnalyzeAgentVersion(String analyzeAgentVersion) {
        this.analyzeAgentVersion = analyzeAgentVersion;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    public int getWorkMode() {
        return workMode;
    }

    public void setWorkMode(int workMode) {
        this.workMode = workMode;
    }

    public boolean isIsReleased() {
        return isReleased;
    }

    public void setIsReleased(boolean isReleased) {
        this.isReleased = isReleased;
    }

    public boolean isHasForensics() {
        return hasForensics;
    }

    public void setHasForensics(boolean hasForensics) {
        this.hasForensics = hasForensics;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public SourceBean getSource() {
        return source;
    }

    public void setSource(SourceBean source) {
        this.source = source;
    }

    public List<MatchedPoliciesBean> getMatchedPolicies() {
        return matchedPolicies;
    }

    public void setMatchedPolicies(List<MatchedPoliciesBean> matchedPolicies) {
        this.matchedPolicies = matchedPolicies;
    }

    public List<HistoriesBean> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoriesBean> histories) {
        this.histories = histories;
    }

    public List<DestinationsBean> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationsBean> destinations) {
        this.destinations = destinations;
    }

    public List<AttachmentsBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentsBean> attachments) {
        this.attachments = attachments;
    }

    public static class SourceBean {
        /**
         * displayName : asynctest1\clouds3user50
         * logonName : asynctest1\clouds3user50
         * processId : 0
         * threadId : 0
         */

        private String displayName;
        private String logonName;
        private int processId;
        private int threadId;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getLogonName() {
            return logonName;
        }

        public void setLogonName(String logonName) {
            this.logonName = logonName;
        }

        public int getProcessId() {
            return processId;
        }

        public void setProcessId(int processId) {
            this.processId = processId;
        }

        public int getThreadId() {
            return threadId;
        }

        public void setThreadId(int threadId) {
            this.threadId = threadId;
        }
    }

    public static class MatchedPoliciesBean {
        /**
         * uuid : f2bd8084-d433-4150-843c-00276d2e1a21
         * name : 云应用单目标单通道策略
         * groupName : 默认策略组
         * priority : 1001
         * severityType : 2
         * matchedAction : {"uuid":"b927bcc7-9d8c-46e9-b72d-a5b9153e5e17","name":"保护"}
         * matches : 1
         * isTrickle : false
         * isVisible : true
         * matchedRules : [{"uuid":"f0a2338c-d266-4101-a1c9-3a979489efa4","name":"文件指纹","matchedConditions":[{"uuid":"6caba887-c71b-400e-98b5-de0658b4c115","elementType":7,"isTraditionalMatching":false,"matchedElements":[{"uuid":"20aae7c5-78cc-41fb-9b73-88fe53b18091","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"DLPencrypt.rar","locationTypesPath":"4","locationType":4,"contentType":1033,"encodeType":"UnknownEncoding","contentSize":798779,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"//file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":true,"isEncryptedFile":true,"isArchiveFile":true}]}]}]}]
         */

        private String uuid;
        private String name;
        private String groupName;
        private int priority;
        private int severityType;
        private MatchedActionBean matchedAction;
        private int matches;
        private boolean isTrickle;
        private boolean isVisible;
        private List<MatchedRulesBean> matchedRules;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getSeverityType() {
            return severityType;
        }

        public void setSeverityType(int severityType) {
            this.severityType = severityType;
        }

        public MatchedActionBean getMatchedAction() {
            return matchedAction;
        }

        public void setMatchedAction(MatchedActionBean matchedAction) {
            this.matchedAction = matchedAction;
        }

        public int getMatches() {
            return matches;
        }

        public void setMatches(int matches) {
            this.matches = matches;
        }

        public boolean isIsTrickle() {
            return isTrickle;
        }

        public void setIsTrickle(boolean isTrickle) {
            this.isTrickle = isTrickle;
        }

        public boolean isIsVisible() {
            return isVisible;
        }

        public void setIsVisible(boolean isVisible) {
            this.isVisible = isVisible;
        }

        public List<MatchedRulesBean> getMatchedRules() {
            return matchedRules;
        }

        public void setMatchedRules(List<MatchedRulesBean> matchedRules) {
            this.matchedRules = matchedRules;
        }

        public static class MatchedActionBean {
            /**
             * uuid : b927bcc7-9d8c-46e9-b72d-a5b9153e5e17
             * name : 保护
             */

            private String uuid;
            private String name;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class MatchedRulesBean {
            /**
             * uuid : f0a2338c-d266-4101-a1c9-3a979489efa4
             * name : 文件指纹
             * matchedConditions : [{"uuid":"6caba887-c71b-400e-98b5-de0658b4c115","elementType":7,"isTraditionalMatching":false,"matchedElements":[{"uuid":"20aae7c5-78cc-41fb-9b73-88fe53b18091","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"DLPencrypt.rar","locationTypesPath":"4","locationType":4,"contentType":1033,"encodeType":"UnknownEncoding","contentSize":798779,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"//file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":true,"isEncryptedFile":true,"isArchiveFile":true}]}]}]
             */

            private String uuid;
            private String name;
            private List<MatchedConditionsBean> matchedConditions;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<MatchedConditionsBean> getMatchedConditions() {
                return matchedConditions;
            }

            public void setMatchedConditions(List<MatchedConditionsBean> matchedConditions) {
                this.matchedConditions = matchedConditions;
            }

            public static class MatchedConditionsBean {
                /**
                 * uuid : 6caba887-c71b-400e-98b5-de0658b4c115
                 * elementType : 7
                 * isTraditionalMatching : false
                 * matchedElements : [{"uuid":"20aae7c5-78cc-41fb-9b73-88fe53b18091","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"DLPencrypt.rar","locationTypesPath":"4","locationType":4,"contentType":1033,"encodeType":"UnknownEncoding","contentSize":798779,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"//file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":true,"isEncryptedFile":true,"isArchiveFile":true}]}]
                 */

                private String uuid;
                private int elementType;
                private boolean isTraditionalMatching;
                private List<MatchedElementsBean> matchedElements;

                public String getUuid() {
                    return uuid;
                }

                public void setUuid(String uuid) {
                    this.uuid = uuid;
                }

                public int getElementType() {
                    return elementType;
                }

                public void setElementType(int elementType) {
                    this.elementType = elementType;
                }

                public boolean isIsTraditionalMatching() {
                    return isTraditionalMatching;
                }

                public void setIsTraditionalMatching(boolean isTraditionalMatching) {
                    this.isTraditionalMatching = isTraditionalMatching;
                }

                public List<MatchedElementsBean> getMatchedElements() {
                    return matchedElements;
                }

                public void setMatchedElements(List<MatchedElementsBean> matchedElements) {
                    this.matchedElements = matchedElements;
                }

                public static class MatchedElementsBean {
                    /**
                     * uuid : 20aae7c5-78cc-41fb-9b73-88fe53b18091
                     * matches : 1
                     * isTruncated : false
                     * matchedContents : [{"locationNamesPath":"DLPencrypt.rar","locationTypesPath":"4","locationType":4,"contentType":1033,"encodeType":"UnknownEncoding","contentSize":798779,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"//file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":true,"isEncryptedFile":true,"isArchiveFile":true}]
                     */

                    private String uuid;
                    private int matches;
                    private boolean isTruncated;
                    private List<MatchedContentsBean> matchedContents;

                    public String getUuid() {
                        return uuid;
                    }

                    public void setUuid(String uuid) {
                        this.uuid = uuid;
                    }

                    public int getMatches() {
                        return matches;
                    }

                    public void setMatches(int matches) {
                        this.matches = matches;
                    }

                    public boolean isIsTruncated() {
                        return isTruncated;
                    }

                    public void setIsTruncated(boolean isTruncated) {
                        this.isTruncated = isTruncated;
                    }

                    public List<MatchedContentsBean> getMatchedContents() {
                        return matchedContents;
                    }

                    public void setMatchedContents(List<MatchedContentsBean> matchedContents) {
                        this.matchedContents = matchedContents;
                    }

                    public static class MatchedContentsBean {
                        /**
                         * locationNamesPath : DLPencrypt.rar
                         * locationTypesPath : 4
                         * locationType : 4
                         * contentType : 1033
                         * encodeType : UnknownEncoding
                         * contentSize : 798779
                         * matches : 1
                         * similarity : 0.0
                         * matchedFingerprints : [{"filePath":"//file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar","isPreciseMatching":true,"similarity":100}]
                         * isFileSuffixMatch : true
                         * isEncryptedFile : true
                         * isArchiveFile : true
                         */

                        private String locationNamesPath;
                        private String locationTypesPath;
                        private int locationType;
                        private int contentType;
                        private String encodeType;
                        private int contentSize;
                        private int matches;
                        private double similarity;
                        private boolean isFileSuffixMatch;
                        private boolean isEncryptedFile;
                        private boolean isArchiveFile;
                        private List<MatchedFingerprintsBean> matchedFingerprints;

                        public String getLocationNamesPath() {
                            return locationNamesPath;
                        }

                        public void setLocationNamesPath(String locationNamesPath) {
                            this.locationNamesPath = locationNamesPath;
                        }

                        public String getLocationTypesPath() {
                            return locationTypesPath;
                        }

                        public void setLocationTypesPath(String locationTypesPath) {
                            this.locationTypesPath = locationTypesPath;
                        }

                        public int getLocationType() {
                            return locationType;
                        }

                        public void setLocationType(int locationType) {
                            this.locationType = locationType;
                        }

                        public int getContentType() {
                            return contentType;
                        }

                        public void setContentType(int contentType) {
                            this.contentType = contentType;
                        }

                        public String getEncodeType() {
                            return encodeType;
                        }

                        public void setEncodeType(String encodeType) {
                            this.encodeType = encodeType;
                        }

                        public int getContentSize() {
                            return contentSize;
                        }

                        public void setContentSize(int contentSize) {
                            this.contentSize = contentSize;
                        }

                        public int getMatches() {
                            return matches;
                        }

                        public void setMatches(int matches) {
                            this.matches = matches;
                        }

                        public double getSimilarity() {
                            return similarity;
                        }

                        public void setSimilarity(double similarity) {
                            this.similarity = similarity;
                        }

                        public boolean isIsFileSuffixMatch() {
                            return isFileSuffixMatch;
                        }

                        public void setIsFileSuffixMatch(boolean isFileSuffixMatch) {
                            this.isFileSuffixMatch = isFileSuffixMatch;
                        }

                        public boolean isIsEncryptedFile() {
                            return isEncryptedFile;
                        }

                        public void setIsEncryptedFile(boolean isEncryptedFile) {
                            this.isEncryptedFile = isEncryptedFile;
                        }

                        public boolean isIsArchiveFile() {
                            return isArchiveFile;
                        }

                        public void setIsArchiveFile(boolean isArchiveFile) {
                            this.isArchiveFile = isArchiveFile;
                        }

                        public List<MatchedFingerprintsBean> getMatchedFingerprints() {
                            return matchedFingerprints;
                        }

                        public void setMatchedFingerprints(List<MatchedFingerprintsBean> matchedFingerprints) {
                            this.matchedFingerprints = matchedFingerprints;
                        }

                        public static class MatchedFingerprintsBean {
                            /**
                             * filePath : //file2.skyguardmis.com/GuestUpload/qa-team/lijialin/ucwi_131_script/ucwifile/DLPencrypt.rar
                             * isPreciseMatching : true
                             * similarity : 100.0
                             */

                            private String filePath;
                            private boolean isPreciseMatching;
                            private double similarity;

                            public String getFilePath() {
                                return filePath;
                            }

                            public void setFilePath(String filePath) {
                                this.filePath = filePath;
                            }

                            public boolean isIsPreciseMatching() {
                                return isPreciseMatching;
                            }

                            public void setIsPreciseMatching(boolean isPreciseMatching) {
                                this.isPreciseMatching = isPreciseMatching;
                            }

                            public double getSimilarity() {
                                return similarity;
                            }

                            public void setSimilarity(double similarity) {
                                this.similarity = similarity;
                            }
                        }
                    }
                }
            }
        }
    }

    public static class HistoriesBean {
        /**
         * operationType : 1
         * adminName : system
         * historyDateTime : 2017-09-13T18:00:51.032+08:00
         */

        private int operationType;
        private String adminName;
        private String historyDateTime;

        public int getOperationType() {
            return operationType;
        }

        public void setOperationType(int operationType) {
            this.operationType = operationType;
        }

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName;
        }

        public String getHistoryDateTime() {
            return historyDateTime;
        }

        public void setHistoryDateTime(String historyDateTime) {
            this.historyDateTime = historyDateTime;
        }
    }

    public static class DestinationsBean {
        /**
         * displayName : 第一云应用
         * processId : 0
         * threadId : 0
         * cloudAppName : 第一云应用
         * cloudAppID : 97e2e930-3516-400c-a67d-f505e06d2ab9
         * destinationType : 1
         * directionType : 2
         * actionType : 0
         * policyAction : 0
         * agentAction : 0
         * isReleased : false
         */

        private String displayName;
        private int processId;
        private int threadId;
        private String cloudAppName;
        private String cloudAppID;
        private int destinationType;
        private int directionType;
        private int actionType;
        private int policyAction;
        private int agentAction;
        private boolean isReleased;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public int getProcessId() {
            return processId;
        }

        public void setProcessId(int processId) {
            this.processId = processId;
        }

        public int getThreadId() {
            return threadId;
        }

        public void setThreadId(int threadId) {
            this.threadId = threadId;
        }

        public String getCloudAppName() {
            return cloudAppName;
        }

        public void setCloudAppName(String cloudAppName) {
            this.cloudAppName = cloudAppName;
        }

        public String getCloudAppID() {
            return cloudAppID;
        }

        public void setCloudAppID(String cloudAppID) {
            this.cloudAppID = cloudAppID;
        }

        public int getDestinationType() {
            return destinationType;
        }

        public void setDestinationType(int destinationType) {
            this.destinationType = destinationType;
        }

        public int getDirectionType() {
            return directionType;
        }

        public void setDirectionType(int directionType) {
            this.directionType = directionType;
        }

        public int getActionType() {
            return actionType;
        }

        public void setActionType(int actionType) {
            this.actionType = actionType;
        }

        public int getPolicyAction() {
            return policyAction;
        }

        public void setPolicyAction(int policyAction) {
            this.policyAction = policyAction;
        }

        public int getAgentAction() {
            return agentAction;
        }

        public void setAgentAction(int agentAction) {
            this.agentAction = agentAction;
        }

        public boolean isIsReleased() {
            return isReleased;
        }

        public void setIsReleased(boolean isReleased) {
            this.isReleased = isReleased;
        }
    }

    public static class AttachmentsBean {
        /**
         * filename : DLPencrypt.rar
         * fileSize : 798779
         * fileType : 0
         */

        private String filename;
        private int fileSize;
        private int fileType;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public int getFileType() {
            return fileType;
        }

        public void setFileType(int fileType) {
            this.fileType = fileType;
        }
    }
}
