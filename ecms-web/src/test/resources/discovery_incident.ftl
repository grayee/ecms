{
    "discovery-incident": {
        "_all": {
            "enabled": false
        },
        "properties": {
            "serialId": {
                "type": "long"
            },
            "transactionId": {
                "type": "keyword"
            },
            "trafficId": {
                "type": "long"
            },
            "incidentType": {
                "type": "byte"
            },
            "detectDateTime": {
                "type": "date",
                "format": "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"
            },
            "detectTime": {
                "type": "date",
                "format": "HH:mm:ss"
            },
            "incidentDateTime": {
                "type": "date",
                "format": "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"
            },
            "incidentTime": {
                "type": "date",
                "format": "HH:mm:ss"
            },
            "severityType": {
                "type": "byte"
            },
            "statusType": {
                "type": "byte"
            },
            "resourceType": {
                "type": "byte"
            },
            "actionType": {
                "type": "byte"
            },
            "policyAction": {
                "type": "byte"
            },
            "agentAction": {
                "type": "byte"
            },
            "isIgnored": {
                "type": "boolean"
            },
            "isLocked": {
                "type": "boolean"
            },
            "tagContent": {
                "type": "keyword"
            },
            "breachContents": {
                "type": "keyword",
                "ignore_above": 512
            },
            "maxMatches": {
                "type": "integer"
            },
            "transactionSize": {
                "type": "long"
            },
            "filePath": {
                "type": "keyword"
            },
            "folderPath": {
                "type": "keyword"
            },
            "filename": {
                "type": "keyword"
            },
            "fileExtension": {
                "type": "keyword"
            },
            "fileSize": {
                "type": "long"
            },
            "fileType": {
                "type": "integer"
            },
            "fileOwner": {
                "type": "keyword"
            },
            "folderOwner": {
                "type": "keyword"
            },
            "fileCreatedTime": {
                "type": "date",
                "format": "yyyy-MM-dd HH:mm:ss"
            },
            "fileModifiedTime": {
                "type": "date",
                "format": "yyyy-MM-dd HH:mm:ss"
            },
            "fileAccessedTime": {
                "type": "date",
                "format": "yyyy-MM-dd HH:mm:ss"
            },
            "filePermission": {
                "type": "keyword"
            },
            "fileChecksum": {
                "type": "keyword"
            },
            "hostname": {
                "type": "keyword"
            },
            "ipAddress": {
                "type": "ip",
                "fields": {
                    "raw": {
                        "type": "keyword"
                    }
                }
            },
            "jobUuid": {
                "type": "keyword"
            },
            "jobName": {
                "type": "keyword"
            },
            "folderType": {
                "type": "byte"
            },
            "segmentId": {
                "type": "integer"
            },
            "segmentTag": {
                "type": "long"
            },
            "deviceType": {
                "type": "byte"
            },
            "operationSystem": {
                "type": "keyword"
            },
            "scriptName": {
                "type": "keyword"
            },
            "deployVersion": {
                "type": "long"
            },
            "detectAgentUuid": {
                "type": "keyword"
            },
            "detectAgentType": {
                "type": "byte"
            },
            "detectAgentHostname": {
                "type": "keyword"
            },
            "detectAgentIp": {
                "type": "ip"
            },
            "detectAgentVersion": {
                "type": "keyword"
            },
            "analyzeAgentUuid": {
                "type": "keyword"
            },
            "analyzeAgentType": {
                "type": "byte"
            },
            "analyzeAgentHostname": {
                "type": "keyword"
            },
            "analyzeAgentIp": {
                "type": "ip"
            },
            "analyzeAgentVersion": {
                "type": "keyword"
            },
            "hierarchy": {
                "type": "integer"
            },
            "matchedPolicies": {
                "properties": {
                    "uuid": {
                        "type": "keyword"
                    },
                    "name": {
                        "type": "keyword"
                    },
                    "groupName": {
                        "type": "keyword"
                    },
                    "priority": {
                        "type": "integer"
                    },
                    "severityType": {
                        "type": "byte"
                    },
                    "matchedAction": {
                        "properties": {
                            "uuid": {
                                "type": "keyword"
                            },
                            "name": {
                                "type": "keyword"
                            }
                        }
                    },
                    "matches": {
                        "type": "integer"
                    },
                    "matchedRules": {
                        "properties": {
                            "uuid": {
                                "type": "keyword"
                            },
                            "name": {
                                "type": "keyword"
                            },
                            "matchedConditions": {
                                "properties": {
                                    "uuid": {
                                        "type": "keyword"
                                    },
                                    "elementType": {
                                        "type": "byte"
                                    },
                                    "isTraditionalMatching": {
                                        "type": "boolean"
                                    },
                                    "matchedElements": {
                                        "properties": {
                                            "uuid": {
                                                "type": "keyword"
                                            },
                                            "name": {
                                                "type": "keyword"
                                            },
                                            "matches": {
                                                "type": "integer"
                                            },
                                            "isTruncated": {
                                                "type": "boolean"
                                            },
                                            "matchedContents": {
                                                "properties": {
                                                    "locationNamesPath": {
                                                        "type": "keyword"
                                                    },
                                                    "locationTypesPath": {
                                                        "type": "keyword"
                                                    },
                                                    "locationType": {
                                                        "type": "byte"
                                                    },
                                                    "contentType": {
                                                        "type": "integer"
                                                    },
                                                    "encodeType": {
                                                        "type": "keyword"
                                                    },
                                                    "contentSize": {
                                                        "type": "long"
                                                    },
                                                    "matches": {
                                                        "type": "integer"
                                                    },
                                                    "compoundTexts": {
                                                        "type": "keyword",
                                                        "ignore_above": 512
                                                    },
                                                    "simplifiedTexts": {
                                                        "type": "keyword",
                                                        "ignore_above": 512
                                                    },
                                                    "similarity": {
                                                        "type": "float"
                                                    },
                                                    "matchedFingerprints": {
                                                        "properties": {
                                                            "filePath": {
                                                                "type": "keyword",
                                                                "ignore_above": 512
                                                            },
                                                            "isPreciseMatching": {
                                                                "type": "boolean"
                                                            },
                                                            "similarity": {
                                                                "type": "float"
                                                            }
                                                        }
                                                    },
                                                    "isFileSuffixMatch": {
                                                        "type": "boolean"
                                                    },
                                                    "isEncryptedFile": {
                                                        "type": "boolean"
                                                    },
                                                    "isArchiveFile": {
                                                        "type": "boolean"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            "histories": {
                "properties": {
                    "operationType": {
                        "type": "byte"
                    },
                    "operationParams": {
                        "type": "keyword",
                        "ignore_above": 256
                    },
                    "comments": {
                        "type": "keyword",
                        "ignore_above": 256
                    },
                    "adminName": {
                        "type": "keyword"
                    },
                    "historyDateTime": {
                        "type": "date",
                        "format": "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"
                    }
                }
            }
        }
    }
}