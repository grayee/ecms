package com.test;

import java.util.List;

/**
 * Created by zhangruigang on 2017/9/23.
 */
public class DiscoveryIncident {

    /**
     * serialId : 19
     * transactionId : 6bc99f05-37f4-47b3-a323-10c6691292f8
     * trafficId : 1200
     * incidentType : 2
     * detectDateTime : 2017-09-20T16:43:33.893+08:00
     * detectTime : 16:43:33
     * incidentDateTime : 2017-09-20T16:45:06.082+08:00
     * incidentTime : 16:45:06
     * severityType : 2
     * statusType : 1
     * actionType : 0
     * policyAction : 0
     * agentAction : 0
     * isIgnored : false
     * breachContents : D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt
     * maxMatches : 1
     * transactionSize : 1637
     * deployVersion : 0
     * detectAgentUuid : 877167776A7C6B71767C6C6864706B6A
     * detectAgentType : 6
     * detectAgentHostname : qa-pad-test.WORKGROUP
     * detectAgentIp : 172.21.21.134
     * detectAgentVersion : 3.16.2.8420
     * analyzeAgentUuid : 2abd856d-13d7-4d7a-a861-3bf0cc27a24f
     * analyzeAgentType : 100
     * analyzeAgentHostname : qa-pad-test.WORKGROUP
     * matchedPolicies : [{"uuid":"e7adb170-9e50-4b70-940b-829e116ed022","name":"文件共享111","groupName":"默认策略组","priority":1001,"severityType":2,"matchedAction":{"uuid":"b927bcc7-9d8c-46e9-b72d-a5b9153e5e17","name":"保护保护保护保护保护保护保护保护保护保护保护保护保护保护保护保护"},"matches":1,"isTrickle":false,"isVisible":true,"matchedRules":[{"uuid":"91874f49-ba93-4565-8925-e617d33cdf3a","name":"文件共享111","matchedConditions":[{"uuid":"dbdc8cca-f480-418a-ba1e-1717f6e094d0","elementType":7,"isTraditionalMatching":false,"matchedElements":[{"uuid":"159ed289-fbd2-452b-aa04-ac7f791a3295","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"政治言论.txt","locationTypesPath":"4","locationType":4,"contentType":180,"encodeType":"UnknownEncoding","contentSize":1637,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":false,"isEncryptedFile":false,"isArchiveFile":false}]}]}]}]}]
     * histories : [{"operationType":1,"adminName":"system","historyDateTime":"2017-09-14T15:28:50.316+08:00"},{"operationType":14,"operationParams":"终端任务终端任务终端任务终端任务终端任务终端任务终端任务终端任务","adminName":"system","historyDateTime":"2017-09-14T15:38:50.524+08:00"}]
     * resourceType : 6
     * isLocked : false
     * filePath : D:\UCSCLite测试用各类型文档-30个文件-140M大小-军测专用\政治言论.txt
     * folderPath : D:\UCSCLite测试用各类型文档-30个文件-140M大小-军测专用
     * filename : 政治言论.txt
     * fileExtension : txt
     * fileSize : 1637
     * fileType : 0
     * fileOwner : BUILTIN\Administrators
     * folderOwner : Administrator
     * fileCreatedTime : 2017-08-28 18:29:06
     * fileModifiedTime : 2016-07-15 18:05:08
     * fileAccessedTime : 2017-08-28 18:29:06
     * filePermission : Administrator:rwx;Guest:r;
     * fileChecksum : 327539A3C031A1F7066BB246BD877938
     * hostname : qa-pad-test.WORKGROUP
     * ipAddress : 172.21.21.134
     * jobUuid : 8bbcc494-13f8-4d5a-a847-aac7c1528683
     * jobName : 终端任务终端任务终端任务终端任务终端任务终端任务终端任务终端任务
     * folderType : 0
     * segmentId : 0
     * segmentTag : 0
     * deviceType : 1
     * operationSystem : Windows 7 sp1 64
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
    private String detectAgentHostname;
    private String detectAgentIp;
    private String detectAgentVersion;
    private String analyzeAgentUuid;
    private int analyzeAgentType;
    private String analyzeAgentHostname;
    private int resourceType;
    private boolean isLocked;
    private String filePath;
    private String folderPath;
    private String filename;
    private String fileExtension;
    private int fileSize;
    private int fileType;
    private String fileOwner;
    private String folderOwner;
    private String fileCreatedTime;
    private String fileModifiedTime;
    private String fileAccessedTime;
    private String filePermission;
    private String fileChecksum;
    private String hostname;
    private String ipAddress;
    private String jobUuid;
    private String jobName;
    private int folderType;
    private int segmentId;
    private int segmentTag;
    private int deviceType;
    private String operationSystem;
    private List<MatchedPoliciesBean> matchedPolicies;
    private List<HistoriesBean> histories;

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

    public String getDetectAgentHostname() {
        return detectAgentHostname;
    }

    public void setDetectAgentHostname(String detectAgentHostname) {
        this.detectAgentHostname = detectAgentHostname;
    }

    public String getDetectAgentIp() {
        return detectAgentIp;
    }

    public void setDetectAgentIp(String detectAgentIp) {
        this.detectAgentIp = detectAgentIp;
    }

    public String getDetectAgentVersion() {
        return detectAgentVersion;
    }

    public void setDetectAgentVersion(String detectAgentVersion) {
        this.detectAgentVersion = detectAgentVersion;
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

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFolderOwner() {
        return folderOwner;
    }

    public void setFolderOwner(String folderOwner) {
        this.folderOwner = folderOwner;
    }

    public String getFileCreatedTime() {
        return fileCreatedTime;
    }

    public void setFileCreatedTime(String fileCreatedTime) {
        this.fileCreatedTime = fileCreatedTime;
    }

    public String getFileModifiedTime() {
        return fileModifiedTime;
    }

    public void setFileModifiedTime(String fileModifiedTime) {
        this.fileModifiedTime = fileModifiedTime;
    }

    public String getFileAccessedTime() {
        return fileAccessedTime;
    }

    public void setFileAccessedTime(String fileAccessedTime) {
        this.fileAccessedTime = fileAccessedTime;
    }

    public String getFilePermission() {
        return filePermission;
    }

    public void setFilePermission(String filePermission) {
        this.filePermission = filePermission;
    }

    public String getFileChecksum() {
        return fileChecksum;
    }

    public void setFileChecksum(String fileChecksum) {
        this.fileChecksum = fileChecksum;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getFolderType() {
        return folderType;
    }

    public void setFolderType(int folderType) {
        this.folderType = folderType;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public int getSegmentTag() {
        return segmentTag;
    }

    public void setSegmentTag(int segmentTag) {
        this.segmentTag = segmentTag;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
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

    public static class MatchedPoliciesBean {
        /**
         * uuid : e7adb170-9e50-4b70-940b-829e116ed022
         * name : 文件共享111
         * groupName : 默认策略组
         * priority : 1001
         * severityType : 2
         * matchedAction : {"uuid":"b927bcc7-9d8c-46e9-b72d-a5b9153e5e17","name":"保护保护保护保护保护保护保护保护保护保护保护保护保护保护保护保护"}
         * matches : 1
         * isTrickle : false
         * isVisible : true
         * matchedRules : [{"uuid":"91874f49-ba93-4565-8925-e617d33cdf3a","name":"文件共享111","matchedConditions":[{"uuid":"dbdc8cca-f480-418a-ba1e-1717f6e094d0","elementType":7,"isTraditionalMatching":false,"matchedElements":[{"uuid":"159ed289-fbd2-452b-aa04-ac7f791a3295","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"政治言论.txt","locationTypesPath":"4","locationType":4,"contentType":180,"encodeType":"UnknownEncoding","contentSize":1637,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":false,"isEncryptedFile":false,"isArchiveFile":false}]}]}]}]
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
             * name : 保护保护保护保护保护保护保护保护保护保护保护保护保护保护保护保护
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
             * uuid : 91874f49-ba93-4565-8925-e617d33cdf3a
             * name : 文件共享111
             * matchedConditions : [{"uuid":"dbdc8cca-f480-418a-ba1e-1717f6e094d0","elementType":7,"isTraditionalMatching":false,"matchedElements":[{"uuid":"159ed289-fbd2-452b-aa04-ac7f791a3295","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"政治言论.txt","locationTypesPath":"4","locationType":4,"contentType":180,"encodeType":"UnknownEncoding","contentSize":1637,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":false,"isEncryptedFile":false,"isArchiveFile":false}]}]}]
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
                 * uuid : dbdc8cca-f480-418a-ba1e-1717f6e094d0
                 * elementType : 7
                 * isTraditionalMatching : false
                 * matchedElements : [{"uuid":"159ed289-fbd2-452b-aa04-ac7f791a3295","matches":1,"isTruncated":false,"matchedContents":[{"locationNamesPath":"政治言论.txt","locationTypesPath":"4","locationType":4,"contentType":180,"encodeType":"UnknownEncoding","contentSize":1637,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":false,"isEncryptedFile":false,"isArchiveFile":false}]}]
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
                     * uuid : 159ed289-fbd2-452b-aa04-ac7f791a3295
                     * matches : 1
                     * isTruncated : false
                     * matchedContents : [{"locationNamesPath":"政治言论.txt","locationTypesPath":"4","locationType":4,"contentType":180,"encodeType":"UnknownEncoding","contentSize":1637,"matches":1,"similarity":0,"matchedFingerprints":[{"filePath":"D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt","isPreciseMatching":true,"similarity":100}],"isFileSuffixMatch":false,"isEncryptedFile":false,"isArchiveFile":false}]
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
                         * locationNamesPath : 政治言论.txt
                         * locationTypesPath : 4
                         * locationType : 4
                         * contentType : 180
                         * encodeType : UnknownEncoding
                         * contentSize : 1637
                         * matches : 1
                         * similarity : 0.0
                         * matchedFingerprints : [{"filePath":"D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt","isPreciseMatching":true,"similarity":100}]
                         * isFileSuffixMatch : false
                         * isEncryptedFile : false
                         * isArchiveFile : false
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
                             * filePath : D:/测试数据/UCSCLite测试用各类型文档-30个文件-140M大小-军测专用/政治言论.txt
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
         * historyDateTime : 2017-09-14T15:28:50.316+08:00
         * operationParams : 终端任务终端任务终端任务终端任务终端任务终端任务终端任务终端任务
         */

        private int operationType;
        private String adminName;
        private String historyDateTime;
        private String operationParams;

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

        public String getOperationParams() {
            return operationParams;
        }

        public void setOperationParams(String operationParams) {
            this.operationParams = operationParams;
        }
    }
}
