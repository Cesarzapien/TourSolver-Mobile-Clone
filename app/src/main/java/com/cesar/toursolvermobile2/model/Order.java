package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Order implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("label")
    private String label;

    @SerializedName("requiredSkills")
    private String[] requiredSkills;

    @SerializedName("allSkillsRequired")
    private String allSkillsRequired;

    @SerializedName("active")
    private String active;

    @SerializedName("quantities")
    private String[] quantities;

    @SerializedName("unloadingDurationPerUnit")
    private String unloadingDurationPerUnit;

    @SerializedName("type")
    private String type;

    @SerializedName("fixedVisitDuration")
    private String fixedVisitDuration;

    @SerializedName("timeWindows")
    private String [] timeWindows;

    @SerializedName("wholeVisitInTimeWindow")
    private String wholeVisitInTimeWindow;

    @SerializedName("punctuality")
    private String punctuality;

    @SerializedName("delayPenaltyPerHour")
    private String delayPenaltyPerHour;

    @SerializedName("frequency")
    private String frequency;

    @SerializedName("minDuration")
    private String minDuration;

    @SerializedName("minPartDuration")
    private String minPartDuration;

    @SerializedName("excludeResources")
    private String[] excludeResources;

    @SerializedName("assignResources")
    private String [] assignResources;

    @SerializedName("assignCosts")
    private String [] assignCosts;

    @SerializedName("documentReferences")
    private String [] documentReferences;

    @SerializedName("courierPenalty")
    private String courierPenalty;

    @SerializedName("travelTimeModifier")
    private TravelTimeModifier travelTimeModifier;

    @SerializedName("sequenceNumber")
    private String sequenceNumber;

    @SerializedName("x")
    private double x;

    @SerializedName("y")
    private double y;

    @SerializedName("resourceCompatibility")
    private String resourceCompatibility;

    @SerializedName("evaluationInfos")
    private EvaluationInfos evaluationInfos;

    @SerializedName("streetSide")
    private String streetSide;

    @SerializedName("geocode")
    private Geocode geocode;

    @SerializedName("tsOrderMaximumSpacing")
    private String tsOrderMaximumSpacing;

    @SerializedName("tsOrderMinimumSpacing")
    private String tsOrderMinimumSpacing;

    @SerializedName("tsOrderLastVisit")
    private String tsOrderLastVisit;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("sector")
    private String sector;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("tsOrderBefore")
    private String tsOrderBefore;

    @SerializedName("tsOrderBeforeMaxTimeSpacing")
    private String tsOrderBeforeMaxTimeSpacing;

    @SerializedName("tsOrderBeforeMinTimeSpacing")
    private String tsOrderBeforeMinTimeSpacing;

    @SerializedName("getNotifications")
    private String getNotifications;

    @SerializedName("tsOrderFixed")
    private String tsOrderFixed;

    @SerializedName("scanItems")
    private String [] scanItems;

    @SerializedName("invoiceId")
    private String invoiceId;

    @SerializedName("originalOperationalId")
    private String originalOperationalId;

    @SerializedName("maxDelayTime")
    private String maxDelayTime;

    @SerializedName("maxDurationBeforeDepotDrop")
    private String maxDurationBeforeDepotDrop;

    @SerializedName("allowedDepots")
    private String allowedDepots;

    @SerializedName("providedProducts")
    private String providedProducts;

    @SerializedName("tsOrderDisjoint")
    private String tsOrderDisjoint;

    @SerializedName("customDataMap")
    private CustomDataMap customDataMap;

    @SerializedName("possibleVisitDays")
    private String [] possibleVisitDays;

    protected Order(Parcel in) {
        id = in.readString();
        label = in.readString();
        requiredSkills = in.createStringArray();
        allSkillsRequired = in.readString();
        active = in.readString();
        quantities = in.createStringArray();
        unloadingDurationPerUnit = in.readString();
        type = in.readString();
        fixedVisitDuration = in.readString();
        timeWindows = in.createStringArray();
        wholeVisitInTimeWindow = in.readString();
        punctuality = in.readString();
        delayPenaltyPerHour = in.readString();
        frequency = in.readString();
        minDuration = in.readString();
        minPartDuration = in.readString();
        excludeResources = in.createStringArray();
        assignResources = in.createStringArray();
        assignCosts = in.createStringArray();
        documentReferences = in.createStringArray();
        courierPenalty = in.readString();
        sequenceNumber = in.readString();
        x = in.readDouble();
        y = in.readDouble();
        resourceCompatibility = in.readString();
        streetSide = in.readString();
        tsOrderMaximumSpacing = in.readString();
        tsOrderMinimumSpacing = in.readString();
        tsOrderLastVisit = in.readString();
        customerId = in.readString();
        sector = in.readString();
        email = in.readString();
        phone = in.readString();
        tsOrderBefore = in.readString();
        tsOrderBeforeMaxTimeSpacing = in.readString();
        tsOrderBeforeMinTimeSpacing = in.readString();
        getNotifications = in.readString();
        tsOrderFixed = in.readString();
        scanItems = in.createStringArray();
        invoiceId = in.readString();
        originalOperationalId = in.readString();
        maxDelayTime = in.readString();
        maxDurationBeforeDepotDrop = in.readString();
        allowedDepots = in.readString();
        providedProducts = in.readString();
        tsOrderDisjoint = in.readString();
        customDataMap = in.readParcelable(CustomDataMap.class.getClassLoader());
        possibleVisitDays = in.createStringArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(label);
        dest.writeStringArray(requiredSkills);
        dest.writeString(allSkillsRequired);
        dest.writeString(active);
        dest.writeStringArray(quantities);
        dest.writeString(unloadingDurationPerUnit);
        dest.writeString(type);
        dest.writeString(fixedVisitDuration);
        dest.writeStringArray(timeWindows);
        dest.writeString(wholeVisitInTimeWindow);
        dest.writeString(punctuality);
        dest.writeString(delayPenaltyPerHour);
        dest.writeString(frequency);
        dest.writeString(minDuration);
        dest.writeString(minPartDuration);
        dest.writeStringArray(excludeResources);
        dest.writeStringArray(assignResources);
        dest.writeStringArray(assignCosts);
        dest.writeStringArray(documentReferences);
        dest.writeString(courierPenalty);
        dest.writeString(sequenceNumber);
        dest.writeDouble(x);
        dest.writeDouble(y);
        dest.writeString(resourceCompatibility);
        dest.writeString(streetSide);
        dest.writeString(tsOrderMaximumSpacing);
        dest.writeString(tsOrderMinimumSpacing);
        dest.writeString(tsOrderLastVisit);
        dest.writeString(customerId);
        dest.writeString(sector);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(tsOrderBefore);
        dest.writeString(tsOrderBeforeMaxTimeSpacing);
        dest.writeString(tsOrderBeforeMinTimeSpacing);
        dest.writeString(getNotifications);
        dest.writeString(tsOrderFixed);
        dest.writeStringArray(scanItems);
        dest.writeString(invoiceId);
        dest.writeString(originalOperationalId);
        dest.writeString(maxDelayTime);
        dest.writeString(maxDurationBeforeDepotDrop);
        dest.writeString(allowedDepots);
        dest.writeString(providedProducts);
        dest.writeString(tsOrderDisjoint);
        dest.writeParcelable(customDataMap, flags);
        dest.writeStringArray(possibleVisitDays);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String[] requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getAllSkillsRequired() {
        return allSkillsRequired;
    }

    public void setAllSkillsRequired(String allSkillsRequired) {
        this.allSkillsRequired = allSkillsRequired;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String[] getQuantities() {
        return quantities;
    }

    public void setQuantities(String[] quantities) {
        this.quantities = quantities;
    }

    public String getUnloadingDurationPerUnit() {
        return unloadingDurationPerUnit;
    }

    public void setUnloadingDurationPerUnit(String unloadingDurationPerUnit) {
        this.unloadingDurationPerUnit = unloadingDurationPerUnit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFixedVisitDuration() {
        return fixedVisitDuration;
    }

    public void setFixedVisitDuration(String fixedVisitDuration) {
        this.fixedVisitDuration = fixedVisitDuration;
    }

    public String[] getTimeWindows() {
        return timeWindows;
    }

    public void setTimeWindows(String[] timeWindows) {
        this.timeWindows = timeWindows;
    }

    public String getWholeVisitInTimeWindow() {
        return wholeVisitInTimeWindow;
    }

    public void setWholeVisitInTimeWindow(String wholeVisitInTimeWindow) {
        this.wholeVisitInTimeWindow = wholeVisitInTimeWindow;
    }

    public String getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(String punctuality) {
        this.punctuality = punctuality;
    }

    public String getDelayPenaltyPerHour() {
        return delayPenaltyPerHour;
    }

    public void setDelayPenaltyPerHour(String delayPenaltyPerHour) {
        this.delayPenaltyPerHour = delayPenaltyPerHour;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(String minDuration) {
        this.minDuration = minDuration;
    }

    public String getMinPartDuration() {
        return minPartDuration;
    }

    public void setMinPartDuration(String minPartDuration) {
        this.minPartDuration = minPartDuration;
    }

    public String[] getExcludeResources() {
        return excludeResources;
    }

    public void setExcludeResources(String[] excludeResources) {
        this.excludeResources = excludeResources;
    }

    public String[] getAssignResources() {
        return assignResources;
    }

    public void setAssignResources(String[] assignResources) {
        this.assignResources = assignResources;
    }

    public String[] getAssignCosts() {
        return assignCosts;
    }

    public void setAssignCosts(String[] assignCosts) {
        this.assignCosts = assignCosts;
    }

    public String[] getDocumentReferences() {
        return documentReferences;
    }

    public void setDocumentReferences(String[] documentReferences) {
        this.documentReferences = documentReferences;
    }

    public String getCourierPenalty() {
        return courierPenalty;
    }

    public void setCourierPenalty(String courierPenalty) {
        this.courierPenalty = courierPenalty;
    }

    public TravelTimeModifier getTravelTimeModifier() {
        return travelTimeModifier;
    }

    public void setTravelTimeModifier(TravelTimeModifier travelTimeModifier) {
        this.travelTimeModifier = travelTimeModifier;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getResourceCompatibility() {
        return resourceCompatibility;
    }

    public void setResourceCompatibility(String resourceCompatibility) {
        this.resourceCompatibility = resourceCompatibility;
    }

    public EvaluationInfos getEvaluationInfos() {
        return evaluationInfos;
    }

    public void setEvaluationInfos(EvaluationInfos evaluationInfos) {
        this.evaluationInfos = evaluationInfos;
    }

    public String getStreetSide() {
        return streetSide;
    }

    public void setStreetSide(String streetSide) {
        this.streetSide = streetSide;
    }

    public Geocode getGeocode() {
        return geocode;
    }

    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    public String getTsOrderMaximumSpacing() {
        return tsOrderMaximumSpacing;
    }

    public void setTsOrderMaximumSpacing(String tsOrderMaximumSpacing) {
        this.tsOrderMaximumSpacing = tsOrderMaximumSpacing;
    }

    public String getTsOrderMinimumSpacing() {
        return tsOrderMinimumSpacing;
    }

    public void setTsOrderMinimumSpacing(String tsOrderMinimumSpacing) {
        this.tsOrderMinimumSpacing = tsOrderMinimumSpacing;
    }

    public String getTsOrderLastVisit() {
        return tsOrderLastVisit;
    }

    public void setTsOrderLastVisit(String tsOrderLastVisit) {
        this.tsOrderLastVisit = tsOrderLastVisit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTsOrderBefore() {
        return tsOrderBefore;
    }

    public void setTsOrderBefore(String tsOrderBefore) {
        this.tsOrderBefore = tsOrderBefore;
    }

    public String getTsOrderBeforeMaxTimeSpacing() {
        return tsOrderBeforeMaxTimeSpacing;
    }

    public void setTsOrderBeforeMaxTimeSpacing(String tsOrderBeforeMaxTimeSpacing) {
        this.tsOrderBeforeMaxTimeSpacing = tsOrderBeforeMaxTimeSpacing;
    }

    public String getTsOrderBeforeMinTimeSpacing() {
        return tsOrderBeforeMinTimeSpacing;
    }

    public void setTsOrderBeforeMinTimeSpacing(String tsOrderBeforeMinTimeSpacing) {
        this.tsOrderBeforeMinTimeSpacing = tsOrderBeforeMinTimeSpacing;
    }

    public String getGetNotifications() {
        return getNotifications;
    }

    public void setGetNotifications(String getNotifications) {
        this.getNotifications = getNotifications;
    }

    public String getTsOrderFixed() {
        return tsOrderFixed;
    }

    public void setTsOrderFixed(String tsOrderFixed) {
        this.tsOrderFixed = tsOrderFixed;
    }

    public String[] getScanItems() {
        return scanItems;
    }

    public void setScanItems(String[] scanItems) {
        this.scanItems = scanItems;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getOriginalOperationalId() {
        return originalOperationalId;
    }

    public void setOriginalOperationalId(String originalOperationalId) {
        this.originalOperationalId = originalOperationalId;
    }

    public String getMaxDelayTime() {
        return maxDelayTime;
    }

    public void setMaxDelayTime(String maxDelayTime) {
        this.maxDelayTime = maxDelayTime;
    }

    public String getMaxDurationBeforeDepotDrop() {
        return maxDurationBeforeDepotDrop;
    }

    public void setMaxDurationBeforeDepotDrop(String maxDurationBeforeDepotDrop) {
        this.maxDurationBeforeDepotDrop = maxDurationBeforeDepotDrop;
    }

    public String getAllowedDepots() {
        return allowedDepots;
    }

    public void setAllowedDepots(String allowedDepots) {
        this.allowedDepots = allowedDepots;
    }

    public String getProvidedProducts() {
        return providedProducts;
    }

    public void setProvidedProducts(String providedProducts) {
        this.providedProducts = providedProducts;
    }

    public String getTsOrderDisjoint() {
        return tsOrderDisjoint;
    }

    public void setTsOrderDisjoint(String tsOrderDisjoint) {
        this.tsOrderDisjoint = tsOrderDisjoint;
    }

    public CustomDataMap getCustomDataMap() {
        return customDataMap;
    }

    public void setCustomDataMap(CustomDataMap customDataMap) {
        this.customDataMap = customDataMap;
    }

    public String[] getPossibleVisitDays() {
        return possibleVisitDays;
    }

    public void setPossibleVisitDays(String[] possibleVisitDays) {
        this.possibleVisitDays = possibleVisitDays;
    }

    // Getters y setters


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", requiredSkills=" + Arrays.toString(requiredSkills) +
                ", allSkillsRequired='" + allSkillsRequired + '\'' +
                ", active='" + active + '\'' +
                ", quantities=" + Arrays.toString(quantities) +
                ", unloadingDurationPerUnit='" + unloadingDurationPerUnit + '\'' +
                ", type='" + type + '\'' +
                ", fixedVisitDuration='" + fixedVisitDuration + '\'' +
                ", timeWindows=" + Arrays.toString(timeWindows) +
                ", wholeVisitInTimeWindow='" + wholeVisitInTimeWindow + '\'' +
                ", punctuality='" + punctuality + '\'' +
                ", delayPenaltyPerHour='" + delayPenaltyPerHour + '\'' +
                ", frequency='" + frequency + '\'' +
                ", minDuration='" + minDuration + '\'' +
                ", minPartDuration='" + minPartDuration + '\'' +
                ", excludeResources=" + Arrays.toString(excludeResources) +
                ", assignResources=" + Arrays.toString(assignResources) +
                ", assignCosts=" + Arrays.toString(assignCosts) +
                ", documentReferences=" + Arrays.toString(documentReferences) +
                ", courierPenalty='" + courierPenalty + '\'' +
                ", travelTimeModifier=" + travelTimeModifier +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", resourceCompatibility='" + resourceCompatibility + '\'' +
                ", evaluationInfos=" + evaluationInfos +
                ", streetSide='" + streetSide + '\'' +
                ", geocode=" + geocode +
                ", tsOrderMaximumSpacing='" + tsOrderMaximumSpacing + '\'' +
                ", tsOrderMinimumSpacing='" + tsOrderMinimumSpacing + '\'' +
                ", tsOrderLastVisit='" + tsOrderLastVisit + '\'' +
                ", customerId='" + customerId + '\'' +
                ", sector='" + sector + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", tsOrderBefore='" + tsOrderBefore + '\'' +
                ", tsOrderBeforeMaxTimeSpacing='" + tsOrderBeforeMaxTimeSpacing + '\'' +
                ", tsOrderBeforeMinTimeSpacing='" + tsOrderBeforeMinTimeSpacing + '\'' +
                ", getNotifications='" + getNotifications + '\'' +
                ", tsOrderFixed='" + tsOrderFixed + '\'' +
                ", scanItems=" + Arrays.toString(scanItems) +
                ", invoiceId='" + invoiceId + '\'' +
                ", originalOperationalId='" + originalOperationalId + '\'' +
                ", maxDelayTime='" + maxDelayTime + '\'' +
                ", maxDurationBeforeDepotDrop='" + maxDurationBeforeDepotDrop + '\'' +
                ", allowedDepots='" + allowedDepots + '\'' +
                ", providedProducts='" + providedProducts + '\'' +
                ", tsOrderDisjoint='" + tsOrderDisjoint + '\'' +
                ", customDataMap=" + customDataMap +
                ", possibleVisitDays=" + Arrays.toString(possibleVisitDays) +
                '}';
    }
}