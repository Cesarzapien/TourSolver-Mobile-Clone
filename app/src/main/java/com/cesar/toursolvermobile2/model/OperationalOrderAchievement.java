package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class OperationalOrderAchievement implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("operationalResourceId")
    private String operationalResourceId;
    @SerializedName("date")
    private long date;
    @SerializedName("plannedOrder")
    private PlannedOrder plannedOrder;
    @SerializedName("order")
    private Order order;
    @SerializedName("start")
    private long start;
    @SerializedName("end")
    private long end;
    @SerializedName("achievementStart")
    private Long achievementStart;
    @SerializedName("achievementEnd")
    private Long achievementEnd;
    @SerializedName("achievementComment")
    private String achievementComment;
    @SerializedName("achievementStartLat")
    private double achievementStartLat;
    @SerializedName("achievementStartLon")
    private double achievementStartLon;
    @SerializedName("achievementEndLat")
    private double achievementEndLat;
    @SerializedName("achievementEndLon")
    private double achievementEndLon;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("signerName")
    private String signerName;

    @SerializedName("signatureSvg")
    private String signatureSvg;

    @SerializedName("signaturePicture")
    private  String signaturePicture;

    @SerializedName("workerSignatureSvg")
    private String workerSignatureSvg;

    @SerializedName("workerSignaturePicture")
    private String workerSignaturePicture;

    @SerializedName("timeWindowEnd")
    private String timeWindowEnd;

    @SerializedName("timeWindowStart")
    private String timeWindowStart;

    @SerializedName("lastSynchroStatusChange")
    private long lastSynchroStatusChange;

    @SerializedName("synchroStatus")
    private String synchroStatus;

    @SerializedName("lon")
    private double lon;

    @SerializedName("lat")
    private double lat;

    @SerializedName("geocode")
    private Geocode geocode;

    @SerializedName("data")
    private Data data;

    @SerializedName("pictures")
    private String [] pictures;

    @SerializedName("canAssignPhoto")
    private boolean canAssignPhoto;

    @SerializedName("simulationId")
    private String simulationId;

    @SerializedName("simulationDayId")
    private String simulationDayId;

    @SerializedName("wishedStart")
    private String wishedStart;

    @SerializedName("wishedEnd")
    private String wishedEnd;

    @SerializedName("appointmentChanged")
    private boolean appointmentChanged;

    @SerializedName("appointmentFixed")
    private boolean appointmentFixed;

    @SerializedName("invoiceSent")
    private boolean invoiceSent;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("documentUrls")
    private String [] documentUrls;

    @SerializedName("organization")
    private String organization;

    @SerializedName("canceledByUser")
    private boolean canceledByUser;

    @SerializedName("canceledByCustomer")
    private boolean canceledByCustomer;

    @SerializedName("rescheduled")
    private boolean rescheduled;

    @SerializedName("rescheduledInSimulation")
    private String rescheduledInSimulation;

    @SerializedName("internalResourceId")
    private String internalResourceId;

    @SerializedName("fuelType")
    private String fuelType;

    @SerializedName("vehicleType")
    private String vehicleType;

    @SerializedName("averageFuelConsumption")
    private String averageFuelConsumption;

    @SerializedName("rescheduleCount")
    private int rescheduleCount;

    @SerializedName("zoneId")
    private String zoneId;

    @SerializedName("report")
    private Report report;

    @SerializedName("isLate")
    private boolean isLate;

    @SerializedName("lateStart")
    private String lateStart;

    @SerializedName("lateNotificationTimeout")
    private String lateNotificationTimeout;

    @SerializedName("tourProgressNotificationSent")
    private boolean tourProgressNotificationSent;

    @SerializedName("etaOrderId")
    private String etaOrderId;

    @SerializedName("followUpShortLink")
    private String followUpShortLink;

    @SerializedName("etaOrderData")
    private String etaOrderData;

    @SerializedName("distance")
    private double distance;

    @SerializedName("scanItems")
    private String [] scanItems;

    @SerializedName("globalScanItemsStatus")
    private String globalScanItemsStatus;

    @SerializedName("numberOfDeliveredItems")
    private int numberOfDeliveredItems;

    @SerializedName("weatherSkyDescription")
    private String weatherSkyDescription;

    @SerializedName("weatherPrecipitationDesc")
    private String weatherPrecipitationDesc;

    @SerializedName("weatherPrecipitationProbability")
    private double weatherPrecipitationProbability;

    @SerializedName("weatherTemperature")
    private double weatherTemperature;

    @SerializedName("weatherSnowFall")
    private double weatherSnowFall;

    @SerializedName("weatherRainFall")
    private double weatherRainFall;

    @SerializedName("weatherWindSpeed")
    private double weatherWindSpeed;

    @SerializedName("weatherSnowCover")
    private double weatherSnowCover;

    @SerializedName("weatherVisibility")
    private double weatherVisibility;

    @SerializedName("missionId")
    private String missionId;

    @SerializedName("groupingId")
    private String groupingId;

    @SerializedName("routeId")
    private String routeId;

    @SerializedName("timeZone")
    private String timeZone;

    protected OperationalOrderAchievement(Parcel in) {
        id = in.readString();
        operationalResourceId = in.readString();
        date = in.readLong();
        plannedOrder = in.readParcelable(PlannedOrder.class.getClassLoader());
        order = in.readParcelable(Order.class.getClassLoader());
        start = in.readLong();
        end = in.readLong();
        if (in.readByte() == 0) {
            achievementStart = null;
        } else {
            achievementStart = in.readLong();
        }
        if (in.readByte() == 0) {
            achievementEnd = null;
        } else {
            achievementEnd = in.readLong();
        }
        achievementComment = in.readString();
        achievementStartLat = in.readDouble();
        achievementStartLon = in.readDouble();
        achievementEndLat = in.readDouble();
        achievementEndLon = in.readDouble();
        status = in.readString();
        type = in.readString();
        signerName = in.readString();
        signatureSvg = in.readString();
        signaturePicture = in.readString();
        workerSignatureSvg = in.readString();
        workerSignaturePicture = in.readString();
        timeWindowEnd = in.readString();
        timeWindowStart = in.readString();
        lastSynchroStatusChange = in.readLong();
        synchroStatus = in.readString();
        lon = in.readDouble();
        lat = in.readDouble();
        data = in.readParcelable(Data.class.getClassLoader());
        pictures = in.createStringArray();
        canAssignPhoto = in.readByte() != 0;
        simulationId = in.readString();
        simulationDayId = in.readString();
        wishedStart = in.readString();
        wishedEnd = in.readString();
        appointmentChanged = in.readByte() != 0;
        appointmentFixed = in.readByte() != 0;
        invoiceSent = in.readByte() != 0;
        customerId = in.readString();
        documentUrls = in.createStringArray();
        organization = in.readString();
        canceledByUser = in.readByte() != 0;
        canceledByCustomer = in.readByte() != 0;
        rescheduled = in.readByte() != 0;
        rescheduledInSimulation = in.readString();
        internalResourceId = in.readString();
        fuelType = in.readString();
        vehicleType = in.readString();
        averageFuelConsumption = in.readString();
        rescheduleCount = in.readInt();
        zoneId = in.readString();
        isLate = in.readByte() != 0;
        lateStart = in.readString();
        lateNotificationTimeout = in.readString();
        tourProgressNotificationSent = in.readByte() != 0;
        etaOrderId = in.readString();
        followUpShortLink = in.readString();
        etaOrderData = in.readString();
        distance = in.readDouble();
        scanItems = in.createStringArray();
        globalScanItemsStatus = in.readString();
        numberOfDeliveredItems = in.readInt();
        weatherSkyDescription = in.readString();
        weatherPrecipitationDesc = in.readString();
        weatherPrecipitationProbability = in.readDouble();
        weatherTemperature = in.readDouble();
        weatherSnowFall = in.readDouble();
        weatherRainFall = in.readDouble();
        weatherWindSpeed = in.readDouble();
        weatherSnowCover = in.readDouble();
        weatherVisibility = in.readDouble();
        missionId = in.readString();
        groupingId = in.readString();
        routeId = in.readString();
        timeZone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(operationalResourceId);
        dest.writeLong(date);
        dest.writeParcelable(plannedOrder, flags);
        dest.writeParcelable(order, flags);
        dest.writeLong(start);
        dest.writeLong(end);
        if (achievementStart == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(achievementStart);
        }
        if (achievementEnd == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(achievementEnd);
        }
        dest.writeString(achievementComment);
        dest.writeDouble(achievementStartLat);
        dest.writeDouble(achievementStartLon);
        dest.writeDouble(achievementEndLat);
        dest.writeDouble(achievementEndLon);
        dest.writeString(status);
        dest.writeString(type);
        dest.writeString(signerName);
        dest.writeString(signatureSvg);
        dest.writeString(signaturePicture);
        dest.writeString(workerSignatureSvg);
        dest.writeString(workerSignaturePicture);
        dest.writeString(timeWindowEnd);
        dest.writeString(timeWindowStart);
        dest.writeLong(lastSynchroStatusChange);
        dest.writeString(synchroStatus);
        dest.writeDouble(lon);
        dest.writeDouble(lat);
        dest.writeParcelable(data, flags);
        dest.writeStringArray(pictures);
        dest.writeByte((byte) (canAssignPhoto ? 1 : 0));
        dest.writeString(simulationId);
        dest.writeString(simulationDayId);
        dest.writeString(wishedStart);
        dest.writeString(wishedEnd);
        dest.writeByte((byte) (appointmentChanged ? 1 : 0));
        dest.writeByte((byte) (appointmentFixed ? 1 : 0));
        dest.writeByte((byte) (invoiceSent ? 1 : 0));
        dest.writeString(customerId);
        dest.writeStringArray(documentUrls);
        dest.writeString(organization);
        dest.writeByte((byte) (canceledByUser ? 1 : 0));
        dest.writeByte((byte) (canceledByCustomer ? 1 : 0));
        dest.writeByte((byte) (rescheduled ? 1 : 0));
        dest.writeString(rescheduledInSimulation);
        dest.writeString(internalResourceId);
        dest.writeString(fuelType);
        dest.writeString(vehicleType);
        dest.writeString(averageFuelConsumption);
        dest.writeInt(rescheduleCount);
        dest.writeString(zoneId);
        dest.writeByte((byte) (isLate ? 1 : 0));
        dest.writeString(lateStart);
        dest.writeString(lateNotificationTimeout);
        dest.writeByte((byte) (tourProgressNotificationSent ? 1 : 0));
        dest.writeString(etaOrderId);
        dest.writeString(followUpShortLink);
        dest.writeString(etaOrderData);
        dest.writeDouble(distance);
        dest.writeStringArray(scanItems);
        dest.writeString(globalScanItemsStatus);
        dest.writeInt(numberOfDeliveredItems);
        dest.writeString(weatherSkyDescription);
        dest.writeString(weatherPrecipitationDesc);
        dest.writeDouble(weatherPrecipitationProbability);
        dest.writeDouble(weatherTemperature);
        dest.writeDouble(weatherSnowFall);
        dest.writeDouble(weatherRainFall);
        dest.writeDouble(weatherWindSpeed);
        dest.writeDouble(weatherSnowCover);
        dest.writeDouble(weatherVisibility);
        dest.writeString(missionId);
        dest.writeString(groupingId);
        dest.writeString(routeId);
        dest.writeString(timeZone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OperationalOrderAchievement> CREATOR = new Creator<OperationalOrderAchievement>() {
        @Override
        public OperationalOrderAchievement createFromParcel(Parcel in) {
            return new OperationalOrderAchievement(in);
        }

        @Override
        public OperationalOrderAchievement[] newArray(int size) {
            return new OperationalOrderAchievement[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationalResourceId() {
        return operationalResourceId;
    }

    public void setOperationalResourceId(String operationalResourceId) {
        this.operationalResourceId = operationalResourceId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public PlannedOrder getPlannedOrder() {
        return plannedOrder;
    }

    public void setPlannedOrder(PlannedOrder plannedOrder) {
        this.plannedOrder = plannedOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public Long getAchievementStart() {
        return achievementStart;
    }

    public void setAchievementStart(Long achievementStart) {
        this.achievementStart = achievementStart;
    }

    public Long getAchievementEnd() {
        return achievementEnd;
    }

    public void setAchievementEnd(Long achievementEnd) {
        this.achievementEnd = achievementEnd;
    }

    public String getAchievementComment() {
        return achievementComment;
    }

    public void setAchievementComment(String achievementComment) {
        this.achievementComment = achievementComment;
    }

    public double getAchievementStartLat() {
        return achievementStartLat;
    }

    public void setAchievementStartLat(double achievementStartLat) {
        this.achievementStartLat = achievementStartLat;
    }

    public double getAchievementStartLon() {
        return achievementStartLon;
    }

    public void setAchievementStartLon(double achievementStartLon) {
        this.achievementStartLon = achievementStartLon;
    }

    public double getAchievementEndLat() {
        return achievementEndLat;
    }

    public void setAchievementEndLat(double achievementEndLat) {
        this.achievementEndLat = achievementEndLat;
    }

    public double getAchievementEndLon() {
        return achievementEndLon;
    }

    public void setAchievementEndLon(double achievementEndLon) {
        this.achievementEndLon = achievementEndLon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getSignatureSvg() {
        return signatureSvg;
    }

    public void setSignatureSvg(String signatureSvg) {
        this.signatureSvg = signatureSvg;
    }

    public String getSignaturePicture() {
        return signaturePicture;
    }

    public void setSignaturePicture(String signaturePicture) {
        this.signaturePicture = signaturePicture;
    }

    public String getWorkerSignatureSvg() {
        return workerSignatureSvg;
    }

    public void setWorkerSignatureSvg(String workerSignatureSvg) {
        this.workerSignatureSvg = workerSignatureSvg;
    }

    public String getWorkerSignaturePicture() {
        return workerSignaturePicture;
    }

    public void setWorkerSignaturePicture(String workerSignaturePicture) {
        this.workerSignaturePicture = workerSignaturePicture;
    }

    public String getTimeWindowEnd() {
        return timeWindowEnd;
    }

    public void setTimeWindowEnd(String timeWindowEnd) {
        this.timeWindowEnd = timeWindowEnd;
    }

    public String getTimeWindowStart() {
        return timeWindowStart;
    }

    public void setTimeWindowStart(String timeWindowStart) {
        this.timeWindowStart = timeWindowStart;
    }

    public long getLastSynchroStatusChange() {
        return lastSynchroStatusChange;
    }

    public void setLastSynchroStatusChange(long lastSynchroStatusChange) {
        this.lastSynchroStatusChange = lastSynchroStatusChange;
    }

    public String getSynchroStatus() {
        return synchroStatus;
    }

    public void setSynchroStatus(String synchroStatus) {
        this.synchroStatus = synchroStatus;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Geocode getGeocode() {
        return geocode;
    }

    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public boolean isCanAssignPhoto() {
        return canAssignPhoto;
    }

    public void setCanAssignPhoto(boolean canAssignPhoto) {
        this.canAssignPhoto = canAssignPhoto;
    }

    public String getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(String simulationId) {
        this.simulationId = simulationId;
    }

    public String getSimulationDayId() {
        return simulationDayId;
    }

    public void setSimulationDayId(String simulationDayId) {
        this.simulationDayId = simulationDayId;
    }

    public String getWishedStart() {
        return wishedStart;
    }

    public void setWishedStart(String wishedStart) {
        this.wishedStart = wishedStart;
    }

    public String getWishedEnd() {
        return wishedEnd;
    }

    public void setWishedEnd(String wishedEnd) {
        this.wishedEnd = wishedEnd;
    }

    public boolean isAppointmentChanged() {
        return appointmentChanged;
    }

    public void setAppointmentChanged(boolean appointmentChanged) {
        this.appointmentChanged = appointmentChanged;
    }

    public boolean isAppointmentFixed() {
        return appointmentFixed;
    }

    public void setAppointmentFixed(boolean appointmentFixed) {
        this.appointmentFixed = appointmentFixed;
    }

    public boolean isInvoiceSent() {
        return invoiceSent;
    }

    public void setInvoiceSent(boolean invoiceSent) {
        this.invoiceSent = invoiceSent;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String[] getDocumentUrls() {
        return documentUrls;
    }

    public void setDocumentUrls(String[] documentUrls) {
        this.documentUrls = documentUrls;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public boolean isCanceledByUser() {
        return canceledByUser;
    }

    public void setCanceledByUser(boolean canceledByUser) {
        this.canceledByUser = canceledByUser;
    }

    public boolean isCanceledByCustomer() {
        return canceledByCustomer;
    }

    public void setCanceledByCustomer(boolean canceledByCustomer) {
        this.canceledByCustomer = canceledByCustomer;
    }

    public boolean isRescheduled() {
        return rescheduled;
    }

    public void setRescheduled(boolean rescheduled) {
        this.rescheduled = rescheduled;
    }

    public String getRescheduledInSimulation() {
        return rescheduledInSimulation;
    }

    public void setRescheduledInSimulation(String rescheduledInSimulation) {
        this.rescheduledInSimulation = rescheduledInSimulation;
    }

    public String getInternalResourceId() {
        return internalResourceId;
    }

    public void setInternalResourceId(String internalResourceId) {
        this.internalResourceId = internalResourceId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(String averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public int getRescheduleCount() {
        return rescheduleCount;
    }

    public void setRescheduleCount(int rescheduleCount) {
        this.rescheduleCount = rescheduleCount;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    public String getLateStart() {
        return lateStart;
    }

    public void setLateStart(String lateStart) {
        this.lateStart = lateStart;
    }

    public String getLateNotificationTimeout() {
        return lateNotificationTimeout;
    }

    public void setLateNotificationTimeout(String lateNotificationTimeout) {
        this.lateNotificationTimeout = lateNotificationTimeout;
    }

    public boolean isTourProgressNotificationSent() {
        return tourProgressNotificationSent;
    }

    public void setTourProgressNotificationSent(boolean tourProgressNotificationSent) {
        this.tourProgressNotificationSent = tourProgressNotificationSent;
    }

    public String getEtaOrderId() {
        return etaOrderId;
    }

    public void setEtaOrderId(String etaOrderId) {
        this.etaOrderId = etaOrderId;
    }

    public String getFollowUpShortLink() {
        return followUpShortLink;
    }

    public void setFollowUpShortLink(String followUpShortLink) {
        this.followUpShortLink = followUpShortLink;
    }

    public String getEtaOrderData() {
        return etaOrderData;
    }

    public void setEtaOrderData(String etaOrderData) {
        this.etaOrderData = etaOrderData;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String[] getScanItems() {
        return scanItems;
    }

    public void setScanItems(String[] scanItems) {
        this.scanItems = scanItems;
    }

    public String getGlobalScanItemsStatus() {
        return globalScanItemsStatus;
    }

    public void setGlobalScanItemsStatus(String globalScanItemsStatus) {
        this.globalScanItemsStatus = globalScanItemsStatus;
    }

    public int getNumberOfDeliveredItems() {
        return numberOfDeliveredItems;
    }

    public void setNumberOfDeliveredItems(int numberOfDeliveredItems) {
        this.numberOfDeliveredItems = numberOfDeliveredItems;
    }

    public String getWeatherSkyDescription() {
        return weatherSkyDescription;
    }

    public void setWeatherSkyDescription(String weatherSkyDescription) {
        this.weatherSkyDescription = weatherSkyDescription;
    }

    public String getWeatherPrecipitationDesc() {
        return weatherPrecipitationDesc;
    }

    public void setWeatherPrecipitationDesc(String weatherPrecipitationDesc) {
        this.weatherPrecipitationDesc = weatherPrecipitationDesc;
    }

    public double getWeatherPrecipitationProbability() {
        return weatherPrecipitationProbability;
    }

    public void setWeatherPrecipitationProbability(double weatherPrecipitationProbability) {
        this.weatherPrecipitationProbability = weatherPrecipitationProbability;
    }

    public double getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(double weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public double getWeatherSnowFall() {
        return weatherSnowFall;
    }

    public void setWeatherSnowFall(double weatherSnowFall) {
        this.weatherSnowFall = weatherSnowFall;
    }

    public double getWeatherRainFall() {
        return weatherRainFall;
    }

    public void setWeatherRainFall(double weatherRainFall) {
        this.weatherRainFall = weatherRainFall;
    }

    public double getWeatherWindSpeed() {
        return weatherWindSpeed;
    }

    public void setWeatherWindSpeed(double weatherWindSpeed) {
        this.weatherWindSpeed = weatherWindSpeed;
    }

    public double getWeatherSnowCover() {
        return weatherSnowCover;
    }

    public void setWeatherSnowCover(double weatherSnowCover) {
        this.weatherSnowCover = weatherSnowCover;
    }

    public double getWeatherVisibility() {
        return weatherVisibility;
    }

    public void setWeatherVisibility(double weatherVisibility) {
        this.weatherVisibility = weatherVisibility;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public String getGroupingId() {
        return groupingId;
    }

    public void setGroupingId(String groupingId) {
        this.groupingId = groupingId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }


// Getters y setters


    @Override
    public String toString() {
        return "OperationalOrderAchievement{" +
                "id='" + id + '\'' +
                ", operationalResourceId='" + operationalResourceId + '\'' +
                ", date=" + date +
                ", plannedOrder=" + plannedOrder +
                ", order=" + order +
                ", start=" + start +
                ", end=" + end +
                ", achievementStart=" + achievementStart +
                ", achievementEnd=" + achievementEnd +
                ", achievementComment='" + achievementComment + '\'' +
                ", achievementStartLat=" + achievementStartLat +
                ", achievementStartLon=" + achievementStartLon +
                ", achievementEndLat=" + achievementEndLat +
                ", achievementEndLon=" + achievementEndLon +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", signerName='" + signerName + '\'' +
                ", signatureSvg='" + signatureSvg + '\'' +
                ", signaturePicture='" + signaturePicture + '\'' +
                ", workerSignatureSvg='" + workerSignatureSvg + '\'' +
                ", workerSignaturePicture='" + workerSignaturePicture + '\'' +
                ", timeWindowEnd='" + timeWindowEnd + '\'' +
                ", timeWindowStart='" + timeWindowStart + '\'' +
                ", lastSynchroStatusChange=" + lastSynchroStatusChange +
                ", synchroStatus='" + synchroStatus + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", geocode=" + geocode +
                ", data=" + data +
                ", pictures=" + Arrays.toString(pictures) +
                ", canAssignPhoto=" + canAssignPhoto +
                ", simulationId='" + simulationId + '\'' +
                ", simulationDayId='" + simulationDayId + '\'' +
                ", wishedStart='" + wishedStart + '\'' +
                ", wishedEnd='" + wishedEnd + '\'' +
                ", appointmentChanged=" + appointmentChanged +
                ", appointmentFixed=" + appointmentFixed +
                ", invoiceSent=" + invoiceSent +
                ", customerId='" + customerId + '\'' +
                ", documentUrls=" + Arrays.toString(documentUrls) +
                ", organization='" + organization + '\'' +
                ", canceledByUser=" + canceledByUser +
                ", canceledByCustomer=" + canceledByCustomer +
                ", rescheduled=" + rescheduled +
                ", rescheduledInSimulation='" + rescheduledInSimulation + '\'' +
                ", internalResourceId='" + internalResourceId + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", averageFuelConsumption='" + averageFuelConsumption + '\'' +
                ", rescheduleCount=" + rescheduleCount +
                ", zoneId='" + zoneId + '\'' +
                ", report=" + report +
                ", isLate=" + isLate +
                ", lateStart='" + lateStart + '\'' +
                ", lateNotificationTimeout='" + lateNotificationTimeout + '\'' +
                ", tourProgressNotificationSent=" + tourProgressNotificationSent +
                ", etaOrderId='" + etaOrderId + '\'' +
                ", followUpShortLink='" + followUpShortLink + '\'' +
                ", etaOrderData='" + etaOrderData + '\'' +
                ", distance=" + distance +
                ", scanItems=" + Arrays.toString(scanItems) +
                ", globalScanItemsStatus='" + globalScanItemsStatus + '\'' +
                ", numberOfDeliveredItems=" + numberOfDeliveredItems +
                ", weatherSkyDescription='" + weatherSkyDescription + '\'' +
                ", weatherPrecipitationDesc='" + weatherPrecipitationDesc + '\'' +
                ", weatherPrecipitationProbability=" + weatherPrecipitationProbability +
                ", weatherTemperature=" + weatherTemperature +
                ", weatherSnowFall=" + weatherSnowFall +
                ", weatherRainFall=" + weatherRainFall +
                ", weatherWindSpeed=" + weatherWindSpeed +
                ", weatherSnowCover=" + weatherSnowCover +
                ", weatherVisibility=" + weatherVisibility +
                ", missionId='" + missionId + '\'' +
                ", groupingId='" + groupingId + '\'' +
                ", routeId='" + routeId + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
