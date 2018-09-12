package com.test;

import java.util.List;

/**
 * Created by zhangruigang on 2017/9/23.
 */
public class Incident {


    private List<SkyguardIncident> skyguardIncident;

    public List<SkyguardIncident> getSkyguardIncident() {
        return skyguardIncident;
    }

    public void setSkyguardIncident(List<SkyguardIncident> skyguardIncident) {
        this.skyguardIncident = skyguardIncident;
    }

    public static class SkyguardIncident {

        private ActionTaken actionTaken;
        private IncidentInfo incidentInfo;
        private NetworkInfo networkInfo;
        private Object properties;
        private List<MatchedPolicies> matchedPolicies;

        public ActionTaken getActionTaken() {
            return actionTaken;
        }

        public void setActionTaken(ActionTaken actionTaken) {
            this.actionTaken = actionTaken;
        }

        public IncidentInfo getIncidentInfo() {
            return incidentInfo;
        }

        public void setIncidentInfo(IncidentInfo incidentInfo) {
            this.incidentInfo = incidentInfo;
        }

        public NetworkInfo getNetworkInfo() {
            return networkInfo;
        }

        public void setNetworkInfo(NetworkInfo networkInfo) {
            this.networkInfo = networkInfo;
        }

        public Object getProperties() {
            return properties;
        }

        public void setProperties(Object properties) {
            this.properties = properties;
        }

        public List<MatchedPolicies> getMatchedPolicies() {
            return matchedPolicies;
        }

        public void setMatchedPolicies(List<MatchedPolicies> matchedPolicies) {
            this.matchedPolicies = matchedPolicies;
        }

        public static class ActionTaken {
            /**
             * agentAction : 2 policyAction : 2
             */

            private int agentAction;
            private int policyAction;

            public int getAgentAction() {
                return agentAction;
            }

            public void setAgentAction(int agentAction) {
                this.agentAction = agentAction;
            }

            public int getPolicyAction() {
                return policyAction;
            }

            public void setPolicyAction(int policyAction) {
                this.policyAction = policyAction;
            }
        }

        public static class IncidentInfo {
            /**
             * agentType : 1 agentUuid : 1 channelType : 2 incidentId : 72978963-16e9-4d9e-aa2d-9569ed896386
             * localDetectedTime : 2016-12-07T14:24:24.018934 speHostname : UCSGvm speUuid :
             * dc777242-be6d-4ab0-bb11-783c200b9e23 speVersion : 1.2.0-045 subject :
             * https://172.16.0.10/cgi-bin/save_file_linux.py totalSize : 3616 trafficId : 2 type :
             * 1 workMode : 2
             */

            private int agentType;
            private String agentUuid;
            private int channelType;
            private String incidentId;
            private String localDetectedTime;
            private String speHostname;
            private String speUuid;
            private String speVersion;
            private String subject;
            private int totalSize;
            private int trafficId;
            private int type;
            private int workMode;

            public int getAgentType() {
                return agentType;
            }

            public void setAgentType(int agentType) {
                this.agentType = agentType;
            }

            public String getAgentUuid() {
                return agentUuid;
            }

            public void setAgentUuid(String agentUuid) {
                this.agentUuid = agentUuid;
            }

            public int getChannelType() {
                return channelType;
            }

            public void setChannelType(int channelType) {
                this.channelType = channelType;
            }

            public String getIncidentId() {
                return incidentId;
            }

            public void setIncidentId(String incidentId) {
                this.incidentId = incidentId;
            }

            public String getLocalDetectedTime() {
                return localDetectedTime;
            }

            public void setLocalDetectedTime(String localDetectedTime) {
                this.localDetectedTime = localDetectedTime;
            }

            public String getSpeHostname() {
                return speHostname;
            }

            public void setSpeHostname(String speHostname) {
                this.speHostname = speHostname;
            }

            public String getSpeUuid() {
                return speUuid;
            }

            public void setSpeUuid(String speUuid) {
                this.speUuid = speUuid;
            }

            public String getSpeVersion() {
                return speVersion;
            }

            public void setSpeVersion(String speVersion) {
                this.speVersion = speVersion;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public int getTotalSize() {
                return totalSize;
            }

            public void setTotalSize(int totalSize) {
                this.totalSize = totalSize;
            }

            public int getTrafficId() {
                return trafficId;
            }

            public void setTrafficId(int trafficId) {
                this.trafficId = trafficId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getWorkMode() {
                return workMode;
            }

            public void setWorkMode(int workMode) {
                this.workMode = workMode;
            }
        }

        public static class NetworkInfo {
            /**
             * destinations : [{"incidentUser":{"ipAddress":"172.16.0.10","url":"https://172.16.0.10/cgi-bin/save_file_linux.py"}}]
             * hasForensics : false incidentAttachments : [{"filename":"skyguard.csv","filesize":3122}]
             * source : {"incidentUser":{"ipAddress":"172.18.1.142"}}
             */

            private boolean hasForensics;
            private Source source;
            private List<Destinations> destinations;
            private List<IncidentAttachments> incidentAttachments;

            public boolean isHasForensics() {
                return hasForensics;
            }

            public void setHasForensics(boolean hasForensics) {
                this.hasForensics = hasForensics;
            }

            public Source getSource() {
                return source;
            }

            public void setSource(Source source) {
                this.source = source;
            }

            public List<Destinations> getDestinations() {
                return destinations;
            }

            public void setDestinations(List<Destinations> destinations) {
                this.destinations = destinations;
            }

            public List<IncidentAttachments> getIncidentAttachments() {
                return incidentAttachments;
            }

            public void setIncidentAttachments(List<IncidentAttachments> incidentAttachments) {
                this.incidentAttachments = incidentAttachments;
            }

            public static class Source {
                /**
                 * incidentUser : {"ipAddress":"172.18.1.142"}
                 */

                private IncidentUser incidentUser;

                public IncidentUser getIncidentUser() {
                    return incidentUser;
                }

                public void setIncidentUser(IncidentUser incidentUser) {
                    this.incidentUser = incidentUser;
                }

                public static class IncidentUser {
                    /**
                     * ipAddress : 172.18.1.142
                     */

                    private String ipAddress;

                    public String getIpAddress() {
                        return ipAddress;
                    }

                    public void setIpAddress(String ipAddress) {
                        this.ipAddress = ipAddress;
                    }
                }
            }

            public static class Destinations {
                /**
                 * incidentUser : {"ipAddress":"172.16.0.10","url":"https://172.16.0.10/cgi-bin/save_file_linux.py"}
                 */

                private IncidentUserBeanX incidentUser;

                public IncidentUserBeanX getIncidentUser() {
                    return incidentUser;
                }

                public void setIncidentUser(IncidentUserBeanX incidentUser) {
                    this.incidentUser = incidentUser;
                }

                public static class IncidentUserBeanX {
                    /**
                     * ipAddress : 172.16.0.10 url : https://172.16.0.10/cgi-bin/save_file_linux.py
                     */

                    private String ipAddress;
                    private String url;

                    public String getIpAddress() {
                        return ipAddress;
                    }

                    public void setIpAddress(String ipAddress) {
                        this.ipAddress = ipAddress;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }

            public static class IncidentAttachments {
                /**
                 * filename : skyguard.csv filesize : 3122
                 */

                private String filename;
                private int filesize;

                public String getFilename() {
                    return filename;
                }

                public void setFilename(String filename) {
                    this.filename = filename;
                }

                public int getFilesize() {
                    return filesize;
                }

                public void setFilesize(int filesize) {
                    this.filesize = filesize;
                }
            }
        }

        public static class MatchedPolicies {
            /**
             * actionSettingName : 保护 actionSettingUuid : b927bcc7-9d8c-46e9-b72d-a5b9153e5e17
             * isTrickle : false matchedRules : [{"matchedConditions":[{"matchedElements":[{"dbFingerPrintUuid":"ebf8b484-1572-47d0-92e3-cb10cffd60d2","isTruncated":false,"matchedContents":[{"contentFormat":180,"contentType":4,"detectedValues":[{"text":"1
             * 刘霖 13321138891 8018 liulin@skyguardmis.com 擅长销售技巧及说话艺术,具有极强的说服力和感染力\u0000"},{"text":"10
             * 赵军 18611438652 8005 zhaojun@skyguardmis.com 田径\u0000"},{"text":"11 齐军 13911137884
             * 8025 qijun@skyguardmis.com 唱歌\u0000"},{"text":"12 陈潞波 18201331978 8052
             * chenlubo@skyguardmis.com 游泳\u0000"},{"text":"13 耿华 18500540024 8052
             * genghua@skyguardmis.com 平板支撑\u0000"},{"text":"14 杜旭 18210100441 8030
             * duxu@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"15 郝林林 13718493533 8023
             * haolinlin@skyguardmis.com 长跑网球\u0000"},{"text":"16 胡立中 18501960037 8029
             * hulizhong@skyguardmis.com 爬山\u0000"},{"text":"17 冷加福 13810381011 8028
             * lengjiafu@skyguardmis.com 侃大山喜欢欣赏美女\u0000"},{"text":"18 李健森 18928016630 8051
             * lijiansen@skyguardmis.com 自由泳\u0000"},{"text":"19 戎光 18511295942 8051
             * rongguang@skyguardmid.com 蛙泳\u0000"},{"text":"2 陈少涵 13910083227 8039
             * chenshaohan@skyguardmis.com 互联网安全技术牛人,越野驾驶\u0000"},{"text":"20 刘佩 13426272972 8021
             * liupei@skyguardmis.com 喜欢高速驾驶\u0000"},{"text":"21 苏正春 18500522558 8031
             * suzhengchun@skyguardmis.com 足球\u0000"},{"text":"22 王连伟 18614234443 8026
             * wanglianwei@skyguardmis.com 冰球\u0000"},{"text":"23 杨龙祥 18810626219 8020
             * yanglongxiang@skyguardmis.com 篮球\u0000"},{"text":"24 叶晨 15010256386 8019
             * yechen@skyguardmis.com 唱歌\u0000"},{"text":"25 于江成 13321195681 8022
             * yujiangcheng@skyguardmis.com 演戏\u0000"},{"text":"26 张树民 18600663060 8027
             * zhangshumin@skyguardmis.com 健身\u0000"},{"text":"27 李仕毅 13699265669 8009
             * lishiyi@skyguardmis.com 俯卧撑\u0000"},{"text":"28 邓广荣 15727330859 8011
             * dengguangrong@skyguardmis.com 跳高\u0000"},{"text":"29 盖永超 13811470017 8008
             * gaiyongchao@skyguardmis.com 唱歌\u0000"},{"text":"3 卓玛 13381156981 8045
             * zhuoma@skyguardmis.com 桌面足球\u0000"},{"text":"30 贺鹏 15810929552 8007
             * hepeng@skyguardmis.com 蛙泳\u0000"},{"text":"31 呼延杰 13261183341 8010
             * huyanjie@skyguardmis.com 平板支撑\u0000"},{"text":"32 候俊峰 13911006372 8014
             * houjunfeng@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"33 张福新 13581982764 8013
             * zhangfuxin@skyguardmis.com 跑步蝶泳登山喝酒\u0000"},{"text":"34 朱雁龙 13609197865 8017
             * zhuyanlong@skyguardmis.com 爬山越野驾驶\u0000"},{"text":"35 段玥 13811940909 8036
             * duanyue@skyguardmis.com 舞蹈游泳\u0000"},{"text":"36 郭政 13466385543 8040
             * guozheng@skyguardmis.com 俯卧撑\u0000"},{"text":"37 许国建 13466736944 8035
             * xuguojian@skyguardmis.com 高速驾驶电影喜剧\u0000"},{"text":"38 李进 13910006981 8015
             * lijin@skyguardmis.com 唱歌\u0000"},{"text":"39 马达 17710370112 8032 mada@skyguardmis.com
             * 游泳\u0000"},{"text":"4 王英哲 18611081558 8047 wangyingzhe@skyguardmis.com
             * 网球\u0000"},{"text":"40 刘孝运 18910125240 8033 liuxiaoyun@skyguardmis.com
             * 网球健身长跑游泳\u0000"},{"text":"41 李娟 13501353273 8024 lijuan@skyguardmis.com
             * 舞蹈\u0000"},{"text":"42 刘静波 18910107366 8016 lijingbo@skyguardmis.com
             * 健身爬山\u0000"},{"text":"43 柴社荣 15650733366 8023 chaisherong@skyguardmis.com
             * 登山\u0000"},{"text":"44 蔡文锋 13683060689 8038 caiwenfeng@skyguardmis.com
             * 刺激项目\u0000"},{"text":"5 梁衍 13501202655 8055 liangyan@skyguardmis.com
             * 棒球\u0000"},{"text":"6 徐超 18910327185 8006 xuchao@skyguardmis.com
             * 吉他摄影\u0000"},{"text":"7 王建召 18612096131 8003 wangjianzhao@skyguardmis.com
             * 演戏表演\u0000"},{"text":"8 刘中砥 13581850298 8002 liuzhongdi@skyguardmis.com
             * 健身\u0000"},{"text":"9 周志鹏 13810939460 8004 zhouzhipeng@skyguardmis.com
             * 俯卧撑\u0000"},{"text":"序号 姓名 手机号码 分机号 公司邮箱 爱好简介\u0000"}],"encodeType":"UnknownEncoding","locationPath":"Other|||Other|||skyguard.csv","locationType":"16|||16|||4","numberOfMatches":45}],"name":"","numberOfMatches":45,"uuid":"56a53dca-6ea3-45e4-9a29-d9c748538911"}],"type":12,"uuid":"ea4572fd-6d96-4db9-ab30-0d7c6d270d61"}],"name":"2333","uuid":"e193493c-1ae9-4419-9b0c-c43d5e3311f7"}]
             * name : db2指纹策略 numberOfMatches : 45 priority : 1 severity : 1 uuid :
             * 22c44586-b852-4062-8f88-2c4a92c6807a
             */

            private String actionSettingName;
            private String actionSettingUuid;
            private boolean isTrickle;
            private String name;
            private int numberOfMatches;
            private int priority;
            private int severity;
            private String uuid;
            private List<MatchedRules> matchedRules;

            public String getActionSettingName() {
                return actionSettingName;
            }

            public void setActionSettingName(String actionSettingName) {
                this.actionSettingName = actionSettingName;
            }

            public String getActionSettingUuid() {
                return actionSettingUuid;
            }

            public void setActionSettingUuid(String actionSettingUuid) {
                this.actionSettingUuid = actionSettingUuid;
            }

            public boolean isIsTrickle() {
                return isTrickle;
            }

            public void setIsTrickle(boolean isTrickle) {
                this.isTrickle = isTrickle;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNumberOfMatches() {
                return numberOfMatches;
            }

            public void setNumberOfMatches(int numberOfMatches) {
                this.numberOfMatches = numberOfMatches;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public int getSeverity() {
                return severity;
            }

            public void setSeverity(int severity) {
                this.severity = severity;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public List<MatchedRules> getMatchedRules() {
                return matchedRules;
            }

            public void setMatchedRules(List<MatchedRules> matchedRules) {
                this.matchedRules = matchedRules;
            }

            public static class MatchedRules {
                /**
                 * matchedConditions : [{"matchedElements":[{"dbFingerPrintUuid":"ebf8b484-1572-47d0-92e3-cb10cffd60d2","isTruncated":false,"matchedContents":[{"contentFormat":180,"contentType":4,"detectedValues":[{"text":"1
                 * 刘霖 13321138891 8018 liulin@skyguardmis.com 擅长销售技巧及说话艺术,具有极强的说服力和感染力\u0000"},{"text":"10
                 * 赵军 18611438652 8005 zhaojun@skyguardmis.com 田径\u0000"},{"text":"11 齐军 13911137884
                 * 8025 qijun@skyguardmis.com 唱歌\u0000"},{"text":"12 陈潞波 18201331978 8052
                 * chenlubo@skyguardmis.com 游泳\u0000"},{"text":"13 耿华 18500540024 8052
                 * genghua@skyguardmis.com 平板支撑\u0000"},{"text":"14 杜旭 18210100441 8030
                 * duxu@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"15 郝林林 13718493533 8023
                 * haolinlin@skyguardmis.com 长跑网球\u0000"},{"text":"16 胡立中 18501960037 8029
                 * hulizhong@skyguardmis.com 爬山\u0000"},{"text":"17 冷加福 13810381011 8028
                 * lengjiafu@skyguardmis.com 侃大山喜欢欣赏美女\u0000"},{"text":"18 李健森 18928016630 8051
                 * lijiansen@skyguardmis.com 自由泳\u0000"},{"text":"19 戎光 18511295942 8051
                 * rongguang@skyguardmid.com 蛙泳\u0000"},{"text":"2 陈少涵 13910083227 8039
                 * chenshaohan@skyguardmis.com 互联网安全技术牛人,越野驾驶\u0000"},{"text":"20 刘佩 13426272972
                 * 8021 liupei@skyguardmis.com 喜欢高速驾驶\u0000"},{"text":"21 苏正春 18500522558 8031
                 * suzhengchun@skyguardmis.com 足球\u0000"},{"text":"22 王连伟 18614234443 8026
                 * wanglianwei@skyguardmis.com 冰球\u0000"},{"text":"23 杨龙祥 18810626219 8020
                 * yanglongxiang@skyguardmis.com 篮球\u0000"},{"text":"24 叶晨 15010256386 8019
                 * yechen@skyguardmis.com 唱歌\u0000"},{"text":"25 于江成 13321195681 8022
                 * yujiangcheng@skyguardmis.com 演戏\u0000"},{"text":"26 张树民 18600663060 8027
                 * zhangshumin@skyguardmis.com 健身\u0000"},{"text":"27 李仕毅 13699265669 8009
                 * lishiyi@skyguardmis.com 俯卧撑\u0000"},{"text":"28 邓广荣 15727330859 8011
                 * dengguangrong@skyguardmis.com 跳高\u0000"},{"text":"29 盖永超 13811470017 8008
                 * gaiyongchao@skyguardmis.com 唱歌\u0000"},{"text":"3 卓玛 13381156981 8045
                 * zhuoma@skyguardmis.com 桌面足球\u0000"},{"text":"30 贺鹏 15810929552 8007
                 * hepeng@skyguardmis.com 蛙泳\u0000"},{"text":"31 呼延杰 13261183341 8010
                 * huyanjie@skyguardmis.com 平板支撑\u0000"},{"text":"32 候俊峰 13911006372 8014
                 * houjunfeng@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"33 张福新 13581982764 8013
                 * zhangfuxin@skyguardmis.com 跑步蝶泳登山喝酒\u0000"},{"text":"34 朱雁龙 13609197865 8017
                 * zhuyanlong@skyguardmis.com 爬山越野驾驶\u0000"},{"text":"35 段玥 13811940909 8036
                 * duanyue@skyguardmis.com 舞蹈游泳\u0000"},{"text":"36 郭政 13466385543 8040
                 * guozheng@skyguardmis.com 俯卧撑\u0000"},{"text":"37 许国建 13466736944 8035
                 * xuguojian@skyguardmis.com 高速驾驶电影喜剧\u0000"},{"text":"38 李进 13910006981 8015
                 * lijin@skyguardmis.com 唱歌\u0000"},{"text":"39 马达 17710370112 8032
                 * mada@skyguardmis.com 游泳\u0000"},{"text":"4 王英哲 18611081558 8047
                 * wangyingzhe@skyguardmis.com 网球\u0000"},{"text":"40 刘孝运 18910125240 8033
                 * liuxiaoyun@skyguardmis.com 网球健身长跑游泳\u0000"},{"text":"41 李娟 13501353273 8024
                 * lijuan@skyguardmis.com 舞蹈\u0000"},{"text":"42 刘静波 18910107366 8016
                 * lijingbo@skyguardmis.com 健身爬山\u0000"},{"text":"43 柴社荣 15650733366 8023
                 * chaisherong@skyguardmis.com 登山\u0000"},{"text":"44 蔡文锋 13683060689 8038
                 * caiwenfeng@skyguardmis.com 刺激项目\u0000"},{"text":"5 梁衍 13501202655 8055
                 * liangyan@skyguardmis.com 棒球\u0000"},{"text":"6 徐超 18910327185 8006
                 * xuchao@skyguardmis.com 吉他摄影\u0000"},{"text":"7 王建召 18612096131 8003
                 * wangjianzhao@skyguardmis.com 演戏表演\u0000"},{"text":"8 刘中砥 13581850298 8002
                 * liuzhongdi@skyguardmis.com 健身\u0000"},{"text":"9 周志鹏 13810939460 8004
                 * zhouzhipeng@skyguardmis.com 俯卧撑\u0000"},{"text":"序号 姓名 手机号码 分机号 公司邮箱
                 * 爱好简介\u0000"}],"encodeType":"UnknownEncoding","locationPath":"Other|||Other|||skyguard.csv","locationType":"16|||16|||4","numberOfMatches":45}],"name":"","numberOfMatches":45,"uuid":"56a53dca-6ea3-45e4-9a29-d9c748538911"}],"type":12,"uuid":"ea4572fd-6d96-4db9-ab30-0d7c6d270d61"}]
                 * name : 2333 uuid : e193493c-1ae9-4419-9b0c-c43d5e3311f7
                 */

                private String name;
                private String uuid;
                private List<MatchedConditions> matchedConditions;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUuid() {
                    return uuid;
                }

                public void setUuid(String uuid) {
                    this.uuid = uuid;
                }

                public List<MatchedConditions> getMatchedConditions() {
                    return matchedConditions;
                }

                public void setMatchedConditions(List<MatchedConditions> matchedConditions) {
                    this.matchedConditions = matchedConditions;
                }

                public static class MatchedConditions {
                    /**
                     * matchedElements : [{"dbFingerPrintUuid":"ebf8b484-1572-47d0-92e3-cb10cffd60d2","isTruncated":false,"matchedContents":[{"contentFormat":180,"contentType":4,"detectedValues":[{"text":"1
                     * 刘霖 13321138891 8018 liulin@skyguardmis.com 擅长销售技巧及说话艺术,具有极强的说服力和感染力\u0000"},{"text":"10
                     * 赵军 18611438652 8005 zhaojun@skyguardmis.com 田径\u0000"},{"text":"11 齐军
                     * 13911137884 8025 qijun@skyguardmis.com 唱歌\u0000"},{"text":"12 陈潞波 18201331978
                     * 8052 chenlubo@skyguardmis.com 游泳\u0000"},{"text":"13 耿华 18500540024 8052
                     * genghua@skyguardmis.com 平板支撑\u0000"},{"text":"14 杜旭 18210100441 8030
                     * duxu@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"15 郝林林 13718493533 8023
                     * haolinlin@skyguardmis.com 长跑网球\u0000"},{"text":"16 胡立中 18501960037 8029
                     * hulizhong@skyguardmis.com 爬山\u0000"},{"text":"17 冷加福 13810381011 8028
                     * lengjiafu@skyguardmis.com 侃大山喜欢欣赏美女\u0000"},{"text":"18 李健森 18928016630 8051
                     * lijiansen@skyguardmis.com 自由泳\u0000"},{"text":"19 戎光 18511295942 8051
                     * rongguang@skyguardmid.com 蛙泳\u0000"},{"text":"2 陈少涵 13910083227 8039
                     * chenshaohan@skyguardmis.com 互联网安全技术牛人,越野驾驶\u0000"},{"text":"20 刘佩 13426272972
                     * 8021 liupei@skyguardmis.com 喜欢高速驾驶\u0000"},{"text":"21 苏正春 18500522558 8031
                     * suzhengchun@skyguardmis.com 足球\u0000"},{"text":"22 王连伟 18614234443 8026
                     * wanglianwei@skyguardmis.com 冰球\u0000"},{"text":"23 杨龙祥 18810626219 8020
                     * yanglongxiang@skyguardmis.com 篮球\u0000"},{"text":"24 叶晨 15010256386 8019
                     * yechen@skyguardmis.com 唱歌\u0000"},{"text":"25 于江成 13321195681 8022
                     * yujiangcheng@skyguardmis.com 演戏\u0000"},{"text":"26 张树民 18600663060 8027
                     * zhangshumin@skyguardmis.com 健身\u0000"},{"text":"27 李仕毅 13699265669 8009
                     * lishiyi@skyguardmis.com 俯卧撑\u0000"},{"text":"28 邓广荣 15727330859 8011
                     * dengguangrong@skyguardmis.com 跳高\u0000"},{"text":"29 盖永超 13811470017 8008
                     * gaiyongchao@skyguardmis.com 唱歌\u0000"},{"text":"3 卓玛 13381156981 8045
                     * zhuoma@skyguardmis.com 桌面足球\u0000"},{"text":"30 贺鹏 15810929552 8007
                     * hepeng@skyguardmis.com 蛙泳\u0000"},{"text":"31 呼延杰 13261183341 8010
                     * huyanjie@skyguardmis.com 平板支撑\u0000"},{"text":"32 候俊峰 13911006372 8014
                     * houjunfeng@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"33 张福新 13581982764
                     * 8013 zhangfuxin@skyguardmis.com 跑步蝶泳登山喝酒\u0000"},{"text":"34 朱雁龙 13609197865
                     * 8017 zhuyanlong@skyguardmis.com 爬山越野驾驶\u0000"},{"text":"35 段玥 13811940909
                     * 8036 duanyue@skyguardmis.com 舞蹈游泳\u0000"},{"text":"36 郭政 13466385543 8040
                     * guozheng@skyguardmis.com 俯卧撑\u0000"},{"text":"37 许国建 13466736944 8035
                     * xuguojian@skyguardmis.com 高速驾驶电影喜剧\u0000"},{"text":"38 李进 13910006981 8015
                     * lijin@skyguardmis.com 唱歌\u0000"},{"text":"39 马达 17710370112 8032
                     * mada@skyguardmis.com 游泳\u0000"},{"text":"4 王英哲 18611081558 8047
                     * wangyingzhe@skyguardmis.com 网球\u0000"},{"text":"40 刘孝运 18910125240 8033
                     * liuxiaoyun@skyguardmis.com 网球健身长跑游泳\u0000"},{"text":"41 李娟 13501353273 8024
                     * lijuan@skyguardmis.com 舞蹈\u0000"},{"text":"42 刘静波 18910107366 8016
                     * lijingbo@skyguardmis.com 健身爬山\u0000"},{"text":"43 柴社荣 15650733366 8023
                     * chaisherong@skyguardmis.com 登山\u0000"},{"text":"44 蔡文锋 13683060689 8038
                     * caiwenfeng@skyguardmis.com 刺激项目\u0000"},{"text":"5 梁衍 13501202655 8055
                     * liangyan@skyguardmis.com 棒球\u0000"},{"text":"6 徐超 18910327185 8006
                     * xuchao@skyguardmis.com 吉他摄影\u0000"},{"text":"7 王建召 18612096131 8003
                     * wangjianzhao@skyguardmis.com 演戏表演\u0000"},{"text":"8 刘中砥 13581850298 8002
                     * liuzhongdi@skyguardmis.com 健身\u0000"},{"text":"9 周志鹏 13810939460 8004
                     * zhouzhipeng@skyguardmis.com 俯卧撑\u0000"},{"text":"序号 姓名 手机号码 分机号 公司邮箱
                     * 爱好简介\u0000"}],"encodeType":"UnknownEncoding","locationPath":"Other|||Other|||skyguard.csv","locationType":"16|||16|||4","numberOfMatches":45}],"name":"","numberOfMatches":45,"uuid":"56a53dca-6ea3-45e4-9a29-d9c748538911"}]
                     * type : 12 uuid : ea4572fd-6d96-4db9-ab30-0d7c6d270d61
                     */

                    private int type;
                    private String uuid;
                    private List<MatchedElements> matchedElements;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getUuid() {
                        return uuid;
                    }

                    public void setUuid(String uuid) {
                        this.uuid = uuid;
                    }

                    public List<MatchedElements> getMatchedElements() {
                        return matchedElements;
                    }

                    public void setMatchedElements(List<MatchedElements> matchedElements) {
                        this.matchedElements = matchedElements;
                    }

                    public static class MatchedElements {
                        /**
                         * dbFingerPrintUuid : ebf8b484-1572-47d0-92e3-cb10cffd60d2 isTruncated :
                         * false matchedContents : [{"contentFormat":180,"contentType":4,"detectedValues":[{"text":"1
                         * 刘霖 13321138891 8018 liulin@skyguardmis.com 擅长销售技巧及说话艺术,具有极强的说服力和感染力\u0000"},{"text":"10
                         * 赵军 18611438652 8005 zhaojun@skyguardmis.com 田径\u0000"},{"text":"11 齐军
                         * 13911137884 8025 qijun@skyguardmis.com 唱歌\u0000"},{"text":"12 陈潞波
                         * 18201331978 8052 chenlubo@skyguardmis.com 游泳\u0000"},{"text":"13 耿华
                         * 18500540024 8052 genghua@skyguardmis.com 平板支撑\u0000"},{"text":"14 杜旭
                         * 18210100441 8030 duxu@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"15 郝林林
                         * 13718493533 8023 haolinlin@skyguardmis.com 长跑网球\u0000"},{"text":"16 胡立中
                         * 18501960037 8029 hulizhong@skyguardmis.com 爬山\u0000"},{"text":"17 冷加福
                         * 13810381011 8028 lengjiafu@skyguardmis.com 侃大山喜欢欣赏美女\u0000"},{"text":"18
                         * 李健森 18928016630 8051 lijiansen@skyguardmis.com 自由泳\u0000"},{"text":"19 戎光
                         * 18511295942 8051 rongguang@skyguardmid.com 蛙泳\u0000"},{"text":"2 陈少涵
                         * 13910083227 8039 chenshaohan@skyguardmis.com 互联网安全技术牛人,越野驾驶\u0000"},{"text":"20
                         * 刘佩 13426272972 8021 liupei@skyguardmis.com 喜欢高速驾驶\u0000"},{"text":"21 苏正春
                         * 18500522558 8031 suzhengchun@skyguardmis.com 足球\u0000"},{"text":"22 王连伟
                         * 18614234443 8026 wanglianwei@skyguardmis.com 冰球\u0000"},{"text":"23 杨龙祥
                         * 18810626219 8020 yanglongxiang@skyguardmis.com 篮球\u0000"},{"text":"24 叶晨
                         * 15010256386 8019 yechen@skyguardmis.com 唱歌\u0000"},{"text":"25 于江成
                         * 13321195681 8022 yujiangcheng@skyguardmis.com 演戏\u0000"},{"text":"26 张树民
                         * 18600663060 8027 zhangshumin@skyguardmis.com 健身\u0000"},{"text":"27 李仕毅
                         * 13699265669 8009 lishiyi@skyguardmis.com 俯卧撑\u0000"},{"text":"28 邓广荣
                         * 15727330859 8011 dengguangrong@skyguardmis.com 跳高\u0000"},{"text":"29 盖永超
                         * 13811470017 8008 gaiyongchao@skyguardmis.com 唱歌\u0000"},{"text":"3 卓玛
                         * 13381156981 8045 zhuoma@skyguardmis.com 桌面足球\u0000"},{"text":"30 贺鹏
                         * 15810929552 8007 hepeng@skyguardmis.com 蛙泳\u0000"},{"text":"31 呼延杰
                         * 13261183341 8010 huyanjie@skyguardmis.com 平板支撑\u0000"},{"text":"32 候俊峰
                         * 13911006372 8014 houjunfeng@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"33
                         * 张福新 13581982764 8013 zhangfuxin@skyguardmis.com 跑步蝶泳登山喝酒\u0000"},{"text":"34
                         * 朱雁龙 13609197865 8017 zhuyanlong@skyguardmis.com 爬山越野驾驶\u0000"},{"text":"35
                         * 段玥 13811940909 8036 duanyue@skyguardmis.com 舞蹈游泳\u0000"},{"text":"36 郭政
                         * 13466385543 8040 guozheng@skyguardmis.com 俯卧撑\u0000"},{"text":"37 许国建
                         * 13466736944 8035 xuguojian@skyguardmis.com 高速驾驶电影喜剧\u0000"},{"text":"38
                         * 李进 13910006981 8015 lijin@skyguardmis.com 唱歌\u0000"},{"text":"39 马达
                         * 17710370112 8032 mada@skyguardmis.com 游泳\u0000"},{"text":"4 王英哲
                         * 18611081558 8047 wangyingzhe@skyguardmis.com 网球\u0000"},{"text":"40 刘孝运
                         * 18910125240 8033 liuxiaoyun@skyguardmis.com 网球健身长跑游泳\u0000"},{"text":"41
                         * 李娟 13501353273 8024 lijuan@skyguardmis.com 舞蹈\u0000"},{"text":"42 刘静波
                         * 18910107366 8016 lijingbo@skyguardmis.com 健身爬山\u0000"},{"text":"43 柴社荣
                         * 15650733366 8023 chaisherong@skyguardmis.com 登山\u0000"},{"text":"44 蔡文锋
                         * 13683060689 8038 caiwenfeng@skyguardmis.com 刺激项目\u0000"},{"text":"5 梁衍
                         * 13501202655 8055 liangyan@skyguardmis.com 棒球\u0000"},{"text":"6 徐超
                         * 18910327185 8006 xuchao@skyguardmis.com 吉他摄影\u0000"},{"text":"7 王建召
                         * 18612096131 8003 wangjianzhao@skyguardmis.com 演戏表演\u0000"},{"text":"8 刘中砥
                         * 13581850298 8002 liuzhongdi@skyguardmis.com 健身\u0000"},{"text":"9 周志鹏
                         * 13810939460 8004 zhouzhipeng@skyguardmis.com 俯卧撑\u0000"},{"text":"序号 姓名
                         * 手机号码 分机号 公司邮箱 爱好简介\u0000"}],"encodeType":"UnknownEncoding","locationPath":"Other|||Other|||skyguard.csv","locationType":"16|||16|||4","numberOfMatches":45}]
                         * name : numberOfMatches : 45 uuid : 56a53dca-6ea3-45e4-9a29-d9c748538911
                         */

                        private String dbFingerPrintUuid;
                        private boolean isTruncated;
                        private String name;
                        private int numberOfMatches;
                        private String uuid;
                        private List<MatchedContents> matchedContents;

                        public String getDbFingerPrintUuid() {
                            return dbFingerPrintUuid;
                        }

                        public void setDbFingerPrintUuid(String dbFingerPrintUuid) {
                            this.dbFingerPrintUuid = dbFingerPrintUuid;
                        }

                        public boolean isIsTruncated() {
                            return isTruncated;
                        }

                        public void setIsTruncated(boolean isTruncated) {
                            this.isTruncated = isTruncated;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public int getNumberOfMatches() {
                            return numberOfMatches;
                        }

                        public void setNumberOfMatches(int numberOfMatches) {
                            this.numberOfMatches = numberOfMatches;
                        }

                        public String getUuid() {
                            return uuid;
                        }

                        public void setUuid(String uuid) {
                            this.uuid = uuid;
                        }

                        public List<MatchedContents> getMatchedContents() {
                            return matchedContents;
                        }

                        public void setMatchedContents(List<MatchedContents> matchedContents) {
                            this.matchedContents = matchedContents;
                        }

                        public static class MatchedContents {
                            /**
                             * contentFormat : 180 contentType : 4 detectedValues : [{"text":"1 刘霖
                             * 13321138891 8018 liulin@skyguardmis.com 擅长销售技巧及说话艺术,具有极强的说服力和感染力\u0000"},{"text":"10
                             * 赵军 18611438652 8005 zhaojun@skyguardmis.com 田径\u0000"},{"text":"11 齐军
                             * 13911137884 8025 qijun@skyguardmis.com 唱歌\u0000"},{"text":"12 陈潞波
                             * 18201331978 8052 chenlubo@skyguardmis.com 游泳\u0000"},{"text":"13 耿华
                             * 18500540024 8052 genghua@skyguardmis.com 平板支撑\u0000"},{"text":"14 杜旭
                             * 18210100441 8030 duxu@skyguardmis.com 引体向上唱歌吉他钢琴\u0000"},{"text":"15
                             * 郝林林 13718493533 8023 haolinlin@skyguardmis.com 长跑网球\u0000"},{"text":"16
                             * 胡立中 18501960037 8029 hulizhong@skyguardmis.com 爬山\u0000"},{"text":"17
                             * 冷加福 13810381011 8028 lengjiafu@skyguardmis.com 侃大山喜欢欣赏美女\u0000"},{"text":"18
                             * 李健森 18928016630 8051 lijiansen@skyguardmis.com 自由泳\u0000"},{"text":"19
                             * 戎光 18511295942 8051 rongguang@skyguardmid.com 蛙泳\u0000"},{"text":"2
                             * 陈少涵 13910083227 8039 chenshaohan@skyguardmis.com
                             * 互联网安全技术牛人,越野驾驶\u0000"},{"text":"20 刘佩 13426272972 8021
                             * liupei@skyguardmis.com 喜欢高速驾驶\u0000"},{"text":"21 苏正春 18500522558
                             * 8031 suzhengchun@skyguardmis.com 足球\u0000"},{"text":"22 王连伟
                             * 18614234443 8026 wanglianwei@skyguardmis.com 冰球\u0000"},{"text":"23
                             * 杨龙祥 18810626219 8020 yanglongxiang@skyguardmis.com
                             * 篮球\u0000"},{"text":"24 叶晨 15010256386 8019 yechen@skyguardmis.com
                             * 唱歌\u0000"},{"text":"25 于江成 13321195681 8022 yujiangcheng@skyguardmis.com
                             * 演戏\u0000"},{"text":"26 张树民 18600663060 8027 zhangshumin@skyguardmis.com
                             * 健身\u0000"},{"text":"27 李仕毅 13699265669 8009 lishiyi@skyguardmis.com
                             * 俯卧撑\u0000"},{"text":"28 邓广荣 15727330859 8011 dengguangrong@skyguardmis.com
                             * 跳高\u0000"},{"text":"29 盖永超 13811470017 8008 gaiyongchao@skyguardmis.com
                             * 唱歌\u0000"},{"text":"3 卓玛 13381156981 8045 zhuoma@skyguardmis.com
                             * 桌面足球\u0000"},{"text":"30 贺鹏 15810929552 8007 hepeng@skyguardmis.com
                             * 蛙泳\u0000"},{"text":"31 呼延杰 13261183341 8010 huyanjie@skyguardmis.com
                             * 平板支撑\u0000"},{"text":"32 候俊峰 13911006372 8014 houjunfeng@skyguardmis.com
                             * 引体向上唱歌吉他钢琴\u0000"},{"text":"33 张福新 13581982764 8013
                             * zhangfuxin@skyguardmis.com 跑步蝶泳登山喝酒\u0000"},{"text":"34 朱雁龙
                             * 13609197865 8017 zhuyanlong@skyguardmis.com 爬山越野驾驶\u0000"},{"text":"35
                             * 段玥 13811940909 8036 duanyue@skyguardmis.com 舞蹈游泳\u0000"},{"text":"36
                             * 郭政 13466385543 8040 guozheng@skyguardmis.com 俯卧撑\u0000"},{"text":"37
                             * 许国建 13466736944 8035 xuguojian@skyguardmis.com 高速驾驶电影喜剧\u0000"},{"text":"38
                             * 李进 13910006981 8015 lijin@skyguardmis.com 唱歌\u0000"},{"text":"39 马达
                             * 17710370112 8032 mada@skyguardmis.com 游泳\u0000"},{"text":"4 王英哲
                             * 18611081558 8047 wangyingzhe@skyguardmis.com 网球\u0000"},{"text":"40
                             * 刘孝运 18910125240 8033 liuxiaoyun@skyguardmis.com
                             * 网球健身长跑游泳\u0000"},{"text":"41 李娟 13501353273 8024
                             * lijuan@skyguardmis.com 舞蹈\u0000"},{"text":"42 刘静波 18910107366 8016
                             * lijingbo@skyguardmis.com 健身爬山\u0000"},{"text":"43 柴社荣 15650733366
                             * 8023 chaisherong@skyguardmis.com 登山\u0000"},{"text":"44 蔡文锋
                             * 13683060689 8038 caiwenfeng@skyguardmis.com 刺激项目\u0000"},{"text":"5
                             * 梁衍 13501202655 8055 liangyan@skyguardmis.com 棒球\u0000"},{"text":"6 徐超
                             * 18910327185 8006 xuchao@skyguardmis.com 吉他摄影\u0000"},{"text":"7 王建召
                             * 18612096131 8003 wangjianzhao@skyguardmis.com 演戏表演\u0000"},{"text":"8
                             * 刘中砥 13581850298 8002 liuzhongdi@skyguardmis.com 健身\u0000"},{"text":"9
                             * 周志鹏 13810939460 8004 zhouzhipeng@skyguardmis.com
                             * 俯卧撑\u0000"},{"text":"序号 姓名 手机号码 分机号 公司邮箱 爱好简介\u0000"}] encodeType :
                             * UnknownEncoding locationPath : Other|||Other|||skyguard.csv
                             * locationType : 16|||16|||4 numberOfMatches : 45
                             */

                            private int contentFormat;
                            private int contentType;
                            private String encodeType;
                            private String locationPath;
                            private String locationType;
                            private int numberOfMatches;
                            private List<DetectedValues> detectedValues;

                            public int getContentFormat() {
                                return contentFormat;
                            }

                            public void setContentFormat(int contentFormat) {
                                this.contentFormat = contentFormat;
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

                            public String getLocationPath() {
                                return locationPath;
                            }

                            public void setLocationPath(String locationPath) {
                                this.locationPath = locationPath;
                            }

                            public String getLocationType() {
                                return locationType;
                            }

                            public void setLocationType(String locationType) {
                                this.locationType = locationType;
                            }

                            public int getNumberOfMatches() {
                                return numberOfMatches;
                            }

                            public void setNumberOfMatches(int numberOfMatches) {
                                this.numberOfMatches = numberOfMatches;
                            }

                            public List<DetectedValues> getDetectedValues() {
                                return detectedValues;
                            }

                            public void setDetectedValues(List<DetectedValues> detectedValues) {
                                this.detectedValues = detectedValues;
                            }

                            public static class DetectedValues {
                                /**
                                 * text : 1 刘霖 13321138891 8018 liulin@skyguardmis.com
                                 * 擅长销售技巧及说话艺术,具有极强的说服力和感染力
                                 */

                                private String text;

                                public String getText() {
                                    return text;
                                }

                                public void setText(String text) {
                                    this.text = text;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
