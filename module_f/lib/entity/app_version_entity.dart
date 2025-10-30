class appVersionEntity {
  String? versionForecast;
  String? versionDesc;
  String? appId;
  int? channel;
  String? downloadUrl;
  String? osType;
  String? versionName;
  bool? isForceUpdate;
  int? versionCode;
  String? timestamp;

  appVersionEntity(
      {this.versionForecast,
        this.versionDesc,
        this.appId,
        this.channel,
        this.downloadUrl,
        this.osType,
        this.versionName,
        this.isForceUpdate,
        this.versionCode,
        this.timestamp});

  appVersionEntity.fromJson(Map<String, dynamic> json) {
    versionForecast = json['versionForecast'];
    versionDesc = json['versionDesc'];
    appId = json['appId'];
    channel = json['channel'];
    downloadUrl = json['downloadUrl'];
    osType = json['osType'];
    versionName = json['versionName'];
    isForceUpdate = json['isForceUpdate'];
    versionCode = json['versionCode'];
    timestamp = json['timestamp'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['versionForecast'] = versionForecast;
    data['versionDesc'] = versionDesc;
    data['appId'] = appId;
    data['channel'] = channel;
    data['downloadUrl'] = downloadUrl;
    data['osType'] = osType;
    data['versionName'] = versionName;
    data['isForceUpdate'] = isForceUpdate;
    data['versionCode'] = versionCode;
    data['timestamp'] = timestamp;
    return data;
  }
}