package com.erp.controller;

import com.erp.request.*;

import com.erp.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "MasterController", description = "Controller for handling All Master")
@RequestMapping("/master")
@RequiredArgsConstructor
public class MasterController {

    private final MasMaritalStatusService maritalStatusService;

    private final MasRelationalStatusService relationalService;

    private final MasLeaveService leaveService;

    private final MasQualificationService qualificationService;

    private final MasInstituteService instituteService;

    private final MasCourseService courseService;

    private final MasReligionService religionService;

    private final MasHolidayService holidayService;

    private final MasCurrencyService currencyService;    //no UI

    private final MasCountryService countryService;  //no UI

    private final MasStateService stateService;

    private final MasDistrictService districtService;

    private final MasTalukaService talukaService;

    private final MasVillageService villageService;

    private final MasCasteService casteService;

    private final MasSubcasteService subcasteService;

    private final MasGradeService gradeService;//no UI for Designation as Grade

    private  final MasTitleService titleService;

    private final MasCapacityService capacityService;//no UI for Centre Master as Capacity UOM

    private final MasCategoryService categoryService;

    private final MasEmployeeStatusService employeeStatusService;

    private final MasExternalAgencyTypeService agencyTypeService;

    private final MasAssetCategoryService assetCategoryService;

    private final MasAssetStatusService assetStatusService;//No UI for AssetDetails Master as Status

    private final MasCentreService centreService;

    private final MasStoreItemGroupService groupService;

    private final MasVendorTypeService vendorTypeService;

    private final MasItemCategoryService itemCategoryService;

    private final MasItemSubcategoryService subcategoryService;

    //-------------------------------------------Mas Marital Status-----------------------------------------------------//

    @PostMapping("/marital-status/create")
    public ResponseEntity<?> addMaritalStatus(@Valid @RequestBody MasMaritalStatusRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maritalStatusService.addMaritalStatus(request));
    }

    @PutMapping("/marital-status/update/{id}")
    public ResponseEntity<?> updateMaritalStatus( @PathVariable Long id,@Valid @RequestBody MasMaritalStatusRequest request){
        return  ResponseEntity.ok(maritalStatusService.editMaritalStatus(id,request));
    }

    @PutMapping("/marital-status/change/{id}")
    public ResponseEntity<?> changeMaritalStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(maritalStatusService.changeMaritalStatus(id, status));
    }


    @GetMapping("/marital-status/{id}")
    public ResponseEntity<?> getMaritalStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(maritalStatusService.getMaritalStatusById(id));
    }

    @GetMapping("/marital-status/all/{flag}")
    public ResponseEntity<?> getAllMaritalStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(maritalStatusService.getAllMaritalStatuses(flag));
    }


    //----------------------------------------------Mas Relation Status _____________________________________________________//


    @PostMapping("/relation-status/create")
    public ResponseEntity<?> addRelationalStatus(@Valid @RequestBody MasRelationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(relationalService.addRelation(request));
    }

    @PutMapping("/relation-status/update/{id}")
    public ResponseEntity<?> updateRelationalStatus(@PathVariable Long id,@Valid @RequestBody MasRelationRequest request){
        return  ResponseEntity.ok(relationalService.updateRelation(id,request));
    }

    @PutMapping("/relation-status/change/{id}")
    public ResponseEntity<?> changeRelationalStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(relationalService.changeRelationsStatus(id,status));
    }


    @GetMapping("/relation-status/{id}")
    public ResponseEntity<?> getRelationalStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(relationalService.findById(id));
    }

    @GetMapping("/relation-status/all/{flag}")
    public ResponseEntity<?> getAllRelationalStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(relationalService.getAllRelations(flag));
    }

    //-------------------------------------------- Mas Leave Status ---------------------------------------------------------//

    @PostMapping("/leave-status/create")
    public ResponseEntity<?> addLeaveStatus(@Valid @RequestBody MasLeaveRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveService.add(request));
    }

    @PutMapping("/leave-status/update/{id}")
    public ResponseEntity<?> updateLeaveStatus(@PathVariable Long id,@Valid @RequestBody MasLeaveRequest request){
        return  ResponseEntity.ok(leaveService.updateById(id,request));
    }

    @PutMapping("/leave-status/change/{id}")
    public ResponseEntity<?> changeLeaveStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(leaveService.changeStatusById(id,status));
    }


    @GetMapping("/leave-status/{id}")
    public ResponseEntity<?> getLeaveStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(leaveService.findById(id));
    }

    @GetMapping("/leave-status/all/{flag}")
    public ResponseEntity<?> getAllLeaveStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(leaveService.getAll(flag));
    }

    //-------------------------------------------  Mas Qualification Status  ----------------------------------------------------//

    @PostMapping("/qualification-status/create")
    public ResponseEntity<?> addQualificationStatus(@Valid @RequestBody MasQualificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(qualificationService.add(request));
    }

    @PutMapping("/qualification-status/update/{id}")
    public ResponseEntity<?> updateQualificationStatus(@PathVariable Long id,@Valid @RequestBody MasQualificationRequest request){
        return  ResponseEntity.ok(qualificationService.updateById(id,request));
    }

    @PutMapping("/qualification-status/change/{id}")
    public ResponseEntity<?> changeQualificationStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(qualificationService.changeStatusById(id,status));
    }


    @GetMapping("/qualification-status/{id}")
    public ResponseEntity<?> getQualificationStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(qualificationService.getById(id));
    }

    @GetMapping("/qualification-status/all/{flag}")
    public ResponseEntity<?> getAllQualificationStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(qualificationService.getAll(flag));
    }

    //-------------------------------------------------------Mas Institute Status----------------------------------------------------//

    @PostMapping("/institute-status/create")
    public ResponseEntity<?> addInstituteStatus(@Valid @RequestBody MasInstituteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instituteService.add(request));
    }

    @PutMapping("/institute-status/update/{id}")
    public ResponseEntity<?> updateInstituteStatus(@PathVariable Long id,@Valid @RequestBody MasInstituteRequest request){
        return  ResponseEntity.ok(instituteService.updateById(id,request));
    }

    @PutMapping("/institute-status/change/{id}")
    public ResponseEntity<?> changeInstituteStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(instituteService.changeStatusById(id,status));
    }


    @GetMapping("/institute-status/{id}")
    public ResponseEntity<?> getInstituteStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(instituteService.getById(id));
    }

    @GetMapping("/institute-status/all/{flag}")
    public ResponseEntity<?> getAllInstituteStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(instituteService.getAll(flag));
    }


    //--------------------------------------------  Mas Course Status ---------------------------------------------------------//


    @PostMapping("/course-status/create")
    public ResponseEntity<?> addCourseStatus(@Valid @RequestBody MasCourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(request));
    }

    @PutMapping("/course-status/update/{id}")
    public ResponseEntity<?> updateCourseStatus(@PathVariable Long id,@Valid @RequestBody MasCourseRequest request){
        return  ResponseEntity.ok(courseService.updateById(id,request));
    }

    @PutMapping("/course-status/change/{id}")
    public ResponseEntity<?> changeCourseStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(courseService.changeStatusById(id,status));
    }


    @GetMapping("/course-status/{id}")
    public ResponseEntity<?> getCourseStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/course-status/all/{flag}")
    public ResponseEntity<?> getAllCourseStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(courseService.getAll(flag));
    }

    //--------------------------------------------- Mas Religion Status ------------------------------------------------------//

    @PostMapping("/religion-status/create")
    public ResponseEntity<?> addReligionStatus(@Valid @RequestBody MasReligionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(religionService.create(request));
    }

    @PutMapping("/religion-status/update/{id}")
    public ResponseEntity<?> updateReligionStatus(@PathVariable Long id,@Valid @RequestBody MasReligionRequest request){
        return  ResponseEntity.ok(religionService.updateById(id,request));
    }

    @PutMapping("/religion-status/change/{id}")
    public ResponseEntity<?> changeReligionStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(religionService.changeStatusById(id,status));
    }


    @GetMapping("/religion-status/{id}")
    public ResponseEntity<?> getReligionStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(religionService.getById(id));
    }

    @GetMapping("/religion-status/all/{flag}")
    public ResponseEntity<?> getAllReligionStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(religionService.getAll(flag));
    }

    //----------------------------------------------- Mas Holiday Status --------------------------------------------------------------------------//


    @PostMapping("/holiday-status/create")
    public ResponseEntity<?> addHolidayStatus(@Valid @RequestBody MasHolidayRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(holidayService.create(request));
    }

    @PutMapping("/holiday-status/update/{id}")
    public ResponseEntity<?> updateHolidayStatus(@PathVariable Long id,@Valid @RequestBody MasHolidayRequest request){
        return  ResponseEntity.ok(holidayService.updateById(id,request));
    }

    @PutMapping("/holiday-status/change/{id}")
    public ResponseEntity<?> changeHolidayStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(holidayService.changeStatusById(id,status));
    }


    @GetMapping("/holiday-status/{id}")
    public ResponseEntity<?> getHolidayStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(holidayService.getById(id));
    }

    @GetMapping("/holiday-status/all/{flag}")
    public ResponseEntity<?> getAllHolidayStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(holidayService.getAll(flag));
    }


    //----------------------------------------------- Mas Currency Status --------------------------------------------------------------------------//


    @PostMapping("/currency-status/create")
    public ResponseEntity<?> addCurrencyStatus(@Valid @RequestBody MasCurrencyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.create(request));
    }

    @PutMapping("/currency-status/update/{id}")
    public ResponseEntity<?> updateCurrencyStatus(@PathVariable Long id,@Valid @RequestBody MasCurrencyRequest request){
        return  ResponseEntity.ok(currencyService.updateById(id,request));
    }

    @PutMapping("/currency-status/change/{id}")
    public ResponseEntity<?> changeCurrencyStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(currencyService.changeStatusById(id,status));
    }


    @GetMapping("/currency-status/{id}")
    public ResponseEntity<?> getCurrencyStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(currencyService.getById(id));
    }

    @GetMapping("/currency-status/all/{flag}")
    public ResponseEntity<?> getAllCurrencyStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(currencyService.getAll(flag));
    }


    //----------------------------------------------- Mas Country Status --------------------------------------------------------------------------//


    @PostMapping("/country-status/create")
    public ResponseEntity<?> addCountryStatus(@Valid @RequestBody MasCountryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.create(request));
    }

    @PutMapping("/country-status/update/{id}")
    public ResponseEntity<?> updateCountryStatus(@PathVariable Long id,@Valid @RequestBody MasCountryRequest request){
        return  ResponseEntity.ok(countryService.updateById(id,request));
    }

    @PutMapping("/country-status/change/{id}")
    public ResponseEntity<?> changeCountryStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(countryService.changeStatusById(id,status));
    }


    @GetMapping("/country-status/{id}")
    public ResponseEntity<?> getCountryStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(countryService.getById(id));
    }

    @GetMapping("/country-status/all/{flag}")
    public ResponseEntity<?> getAllCountryStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(countryService.getAll(flag));
    }


    //----------------------------------------------- Mas Country Status --------------------------------------------------------------------------//


    @PostMapping("/state-status/create")
    public ResponseEntity<?> addStateStatus(@Valid @RequestBody MasStateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.create(request));
    }

    @PutMapping("/state-status/update/{id}")
    public ResponseEntity<?> updateCountryStatus(@PathVariable Long id,@Valid @RequestBody MasStateRequest request){
        return  ResponseEntity.ok(stateService.updateById(id,request));
    }

    @PutMapping("/state-status/change/{id}")
    public ResponseEntity<?> changeStateStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(stateService.changeStatusById(id,status));
    }


    @GetMapping("/state-status/{id}")
    public ResponseEntity<?> getStateStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(stateService.getById(id));
    }

    @GetMapping("/state-status/all/{flag}")
    public ResponseEntity<?> getAllStateStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(stateService.getAll(flag));
    }


    //----------------------------------------------- Mas District Status --------------------------------------------------------------------------//


    @PostMapping("/district-status/create")
    public ResponseEntity<?> addDistrictStatus(@Valid @RequestBody MasDistrictRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(districtService.create(request));
    }

    @PutMapping("/district-status/update/{id}")
    public ResponseEntity<?> updateDistrictStatus(@PathVariable Long id,@Valid @RequestBody MasDistrictRequest request){
        return  ResponseEntity.ok(districtService.updateById(id,request));
    }

    @PutMapping("/district-status/change/{id}")
    public ResponseEntity<?> changeDistrictStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(districtService.changeStatusById(id,status));
    }


    @GetMapping("/district-status/{id}")
    public ResponseEntity<?> getDistrictStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(districtService.getById(id));
    }

    @GetMapping("/district-status/all/{flag}")
    public ResponseEntity<?> getAllDistrictStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(districtService.getAll(flag));
    }


    //----------------------------------------------- Mas Taluka Status --------------------------------------------------------------------------//


    @PostMapping("/taluka-status/create")
    public ResponseEntity<?> addTalukaStatus(@Valid @RequestBody MasTalukaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(talukaService.create(request));
    }

    @PutMapping("/taluka-status/update/{id}")
    public ResponseEntity<?> updateTalukaStatus(@PathVariable Long id, @Valid @RequestBody MasTalukaRequest request){
        return  ResponseEntity.ok(talukaService.updateById(id,request));
    }

    @PutMapping("/taluka-status/change/{id}")
    public ResponseEntity<?> changeTalukaStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(talukaService.changeStatusById(id,status));
    }


    @GetMapping("/taluka-status/{id}")
    public ResponseEntity<?> getTalukaStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(talukaService.getById(id));
    }

    @GetMapping("/taluka-status/all/{flag}")
    public ResponseEntity<?> getAllTalukaStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(talukaService.getAll(flag));
    }


    //----------------------------------------------- Mas Village Status --------------------------------------------------------------------------//


    @PostMapping("/village-status/create")
    public ResponseEntity<?> addVillageStatus(@Valid @RequestBody MasVillageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(villageService.create(request));
    }

    @PutMapping("/village-status/update/{id}")
    public ResponseEntity<?> updateVillageStatus(@PathVariable Long id,@Valid @RequestBody MasVillageRequest request){
        return  ResponseEntity.ok(villageService.updateById(id,request));
    }

    @PutMapping("/village-status/change/{id}")
    public ResponseEntity<?> changeVillageStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(villageService.changeStatusById(id,status));
    }


    @GetMapping("/village-status/{id}")
    public ResponseEntity<?> getVillageStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(villageService.getById(id));
    }

    @GetMapping("/village-status/all/{flag}")
    public ResponseEntity<?> getAllVillageStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(villageService.getAll(flag));
    }


    //----------------------------------------------- Mas Caste Status --------------------------------------------------------------------------//


    @PostMapping("/caste-status/create")
    public ResponseEntity<?> addCasteStatus(@Valid @RequestBody MasCasteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(casteService.create(request));
    }

    @PutMapping("/caste-status/update/{id}")
    public ResponseEntity<?> updateCasteStatus(@PathVariable Long id,@Valid @RequestBody MasCasteRequest request){
        return  ResponseEntity.ok(casteService.updateById(id,request));
    }

    @PutMapping("/caste-status/change/{id}")
    public ResponseEntity<?> changeCasteStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(casteService.changeStatusById(id,status));
    }


    @GetMapping("/caste-status/{id}")
    public ResponseEntity<?> getCasteStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(casteService.getById(id));
    }

    @GetMapping("/caste-status/all/{flag}")
    public ResponseEntity<?> getAllCasteStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(casteService.getAll(flag));
    }



    //----------------------------------------------- Mas Subcaste Status --------------------------------------------------------------------------//


    @PostMapping("/subcaste-status/create")
    public ResponseEntity<?> addSubcasteStatus(@Valid @RequestBody MasSubcasteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subcasteService.create(request));
    }

    @PutMapping("/subcaste-status/update/{id}")
    public ResponseEntity<?> updateSubcasteStatus(@PathVariable Long id,@Valid @RequestBody MasSubcasteRequest request){
        return  ResponseEntity.ok(subcasteService.updateById(id,request));
    }

    @PutMapping("/subcaste-status/change/{id}")
    public ResponseEntity<?> changeSubcasteStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(subcasteService.changeStatusById(id,status));
    }


    @GetMapping("/subcaste-status/{id}")
    public ResponseEntity<?> getSubcasteStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(subcasteService.getById(id));
    }

    @GetMapping("/subcaste-status/all/{flag}")
    public ResponseEntity<?> getAllSubcasteStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(subcasteService.getAll(flag));
    }



    //----------------------------------------------- Mas Grade Status --------------------------------------------------------------------------//


    @PostMapping("/grade-status/create")
    public ResponseEntity<?> addCasteStatus(@Valid @RequestBody MasGradeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.create(request));
    }

    @PutMapping("/grade-status/update/{id}")
    public ResponseEntity<?> updateGradeStatus(@PathVariable Long id,@Valid @RequestBody MasGradeRequest request){
        return  ResponseEntity.ok(gradeService.updateById(id,request));
    }

    @PutMapping("/grade-status/change/{id}")
    public ResponseEntity<?> changeGradeStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(gradeService.changeStatusById(id,status));
    }


    @GetMapping("/grade-status/{id}")
    public ResponseEntity<?> getGradeStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(gradeService.getById(id));
    }

    @GetMapping("/grade-status/all/{flag}")
    public ResponseEntity<?> getAllGradeStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(gradeService.getAll(flag));
    }



    //----------------------------------------------- Mas Title Status --------------------------------------------------------------------------//


    @PostMapping("/title-status/create")
    public ResponseEntity<?> addCasteStatus(@Valid @RequestBody MasTitleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(titleService.create(request));
    }

    @PutMapping("/title-status/update/{id}")
    public ResponseEntity<?> updateTitleStatus(@PathVariable Long id,@Valid @RequestBody MasTitleRequest request){
        return  ResponseEntity.ok(titleService.updateById(id,request));
    }

    @PutMapping("/title-status/change/{id}")
    public ResponseEntity<?> changeTitleStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(titleService.changeStatusById(id,status));
    }


    @GetMapping("/title-status/{id}")
    public ResponseEntity<?> getTitleStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(titleService.getById(id));
    }

    @GetMapping("/title-status/all/{flag}")
    public ResponseEntity<?> getAllTitleStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(titleService.getAll(flag));
    }



    //------------------------------------------ Mas Capacity(Unit Of Measurement) Status -------------------------------------------------------------//


    @PostMapping("/unit-status/create")
    public ResponseEntity<?> addCapacityStatus(@Valid @RequestBody MasCapacityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(capacityService.create(request));
    }

    @PutMapping("/unit-status/update/{id}")
    public ResponseEntity<?> updateCapacityStatus(@PathVariable Long id,@Valid @RequestBody MasCapacityRequest request){
        return  ResponseEntity.ok(capacityService.updateById(id,request));
    }

    @PutMapping("/unit-status/change/{id}")
    public ResponseEntity<?> changeCapacityStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(capacityService.changeStatusById(id,status));
    }


    @GetMapping("/unit-status/{id}")
    public ResponseEntity<?> getCapacityStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(capacityService.getById(id));
    }

    @GetMapping("/unit-status/all/{flag}")
    public ResponseEntity<?> getAllCapacityStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(capacityService.getAll(flag));
    }



    //------------------------------------------ Mas Category Status -------------------------------------------------------------//


    @PostMapping("/category-status/create")
    public ResponseEntity<?> addCategoryStatus(@Valid @RequestBody MasCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request));
    }

    @PutMapping("/category-status/update/{id}")
    public ResponseEntity<?> updateCategoryStatus(@PathVariable Long id,@Valid @RequestBody MasCategoryRequest request){
        return  ResponseEntity.ok(categoryService.updateById(id,request));
    }

    @PutMapping("/category-status/change/{id}")
    public ResponseEntity<?> changeCategoryStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(categoryService.changeStatusById(id,status));
    }


    @GetMapping("/category-status/{id}")
    public ResponseEntity<?> getCategoryStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/category-status/all/{flag}")
    public ResponseEntity<?> getAllCategoryStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(categoryService.getAll(flag));
    }


    //------------------------------------------ Mas Employee Status -------------------------------------------------------------//


    @PostMapping("/emp-status/create")
    public ResponseEntity<?> addEmployeeStatus(@Valid @RequestBody MasEmployeeStatusRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeStatusService.create(request));
    }

    @PutMapping("/emp-status/update/{id}")
    public ResponseEntity<?> updateEmployeeStatus(@PathVariable Long id,@Valid @RequestBody MasEmployeeStatusRequest request){
        return  ResponseEntity.ok(employeeStatusService.updateById(id,request));
    }

    @PutMapping("/emp-status/change/{id}")
    public ResponseEntity<?> changeEmployeeStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(employeeStatusService.changeStatusById(id,status));
    }


    @GetMapping("/emp-status/{id}")
    public ResponseEntity<?> getEmployeeStatusById(@PathVariable Long id){
        return  ResponseEntity.ok(employeeStatusService.getById(id));
    }

    @GetMapping("/emp-status/all/{flag}")
    public ResponseEntity<?> getAllEmployeeStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(employeeStatusService.getAll(flag));
    }



    //------------------------------------------ Mas External Agency Type Status -------------------------------------------------------------//


    @PostMapping("/agency-type-status/create")
    public ResponseEntity<?> addExternalAgencyTypeStatus(@Valid @RequestBody MasExternalAgencyTypeReq request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agencyTypeService.create(request));
    }

    @PutMapping("/agency-type-status/update/{id}")
    public ResponseEntity<?> updateExternalAgencyTypeStatus(@PathVariable Long id,@Valid @RequestBody MasExternalAgencyTypeReq request){
        return  ResponseEntity.ok(agencyTypeService.updateById(id,request));
    }

    @PutMapping("/agency-type-status/change/{id}")
    public ResponseEntity<?> changeExternalAgencyTypeStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(agencyTypeService.changeStatusById(id,status));
    }


    @GetMapping("/agency-type-status/{id}")
    public ResponseEntity<?> getExternalAgencyTypeById(@PathVariable Long id){
        return  ResponseEntity.ok(agencyTypeService.getById(id));
    }

    @GetMapping("/agency-type-status/all/{flag}")
    public ResponseEntity<?> getAllExternalAgencyTypeStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(agencyTypeService.getAll(flag));
    }



    //------------------------------------------ Mas Asset Category Status -------------------------------------------------------------//


    @PostMapping("/asset-category/create")
    public ResponseEntity<?> addAssetCategory(@Valid @RequestBody MasAssetCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assetCategoryService.create(request));
    }

    @PutMapping("/asset-category/update/{id}")
    public ResponseEntity<?> updateAssetCategory(@PathVariable Long id,@Valid @RequestBody MasAssetCategoryRequest request){
        return  ResponseEntity.ok(assetCategoryService.updateById(id,request));
    }

    @PutMapping("/asset-category/change/{id}")
    public ResponseEntity<?> changAssetCategoryStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(assetCategoryService.changeStatusById(id,status));
    }


    @GetMapping("/asset-category/{id}")
    public ResponseEntity<?> getAssetCategoryById(@PathVariable Long id){
        return  ResponseEntity.ok(assetCategoryService.getById(id));
    }

    @GetMapping("/asset-category/all/{flag}")
    public ResponseEntity<?> getAllAssetCategory( @PathVariable Integer flag){
        return  ResponseEntity.ok(assetCategoryService.getAll(flag));
    }



    //------------------------------------------ Mas Asset Status -------------------------------------------------------------//


    @PostMapping("/asset-status/create")
    public ResponseEntity<?> addAssetStatus(@Valid @RequestBody MasAssetStatusRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assetStatusService.create(request));
    }

    @PutMapping("/asset-status/update/{id}")
    public ResponseEntity<?> updateAssetStatus(@PathVariable Long id,@Valid @RequestBody MasAssetStatusRequest request){
        return  ResponseEntity.ok(assetStatusService.updateById(id,request));
    }

    @PutMapping("/asset-status/change/{id}")
    public ResponseEntity<?> changeAssetStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(assetStatusService.changeStatusById(id,status));
    }


    @GetMapping("/asset-status/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable Long id){
        return  ResponseEntity.ok(assetStatusService.getById(id));
    }

    @GetMapping("/asset-status/all/{flag}")
    public ResponseEntity<?> getAllAssetStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(assetStatusService.getAll(flag));
    }



    //------------------------------------------ Mas Centre Status -------------------------------------------------------------//


    @PostMapping("/centre/create")
    public ResponseEntity<?> addCentre(@Valid @RequestBody MasCentreRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(centreService.create(request));
    }

    @PutMapping("/centre/update/{id}")
    public ResponseEntity<?> updateCentre(@PathVariable Long id,@Valid @RequestBody MasCentreRequest request){
        return  ResponseEntity.ok(centreService.updateById(id,request));
    }

    @PutMapping("/centre-status/change/{id}")
    public ResponseEntity<?> changeCentreStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(centreService.changeStatusById(id,status));
    }


    @GetMapping("/centre/{id}")
    public ResponseEntity<?> getCentreById(@PathVariable Long id){
        return  ResponseEntity.ok(centreService.getById(id));
    }

    @GetMapping("/centre/all/{flag}")
    public ResponseEntity<?> getAllCentreStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(centreService.getAll(flag));
    }



    //------------------------------------------ Mas Store Item Group Status -------------------------------------------------------------//


    @PostMapping("/item-group/create")
    public ResponseEntity<?> addStoreItemGroup(@Valid @RequestBody MasStoreItemGrpReq request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.create(request));
    }

    @PutMapping("/item-group/update/{id}")
    public ResponseEntity<?> updateStoreItemGroup(@PathVariable Long id,@Valid @RequestBody MasStoreItemGrpReq request){
        return  ResponseEntity.ok(groupService.updateById(id,request));
    }

    @PutMapping("/item-group/change/{id}")
    public ResponseEntity<?> changeStoreItemGroupStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(groupService.changeStatusById(id,status));
    }


    @GetMapping("/item-group/{id}")
    public ResponseEntity<?> getStoreItemGroupById(@PathVariable Long id){
        return  ResponseEntity.ok(groupService.getById(id));
    }

    @GetMapping("/item-group/all/{flag}")
    public ResponseEntity<?> getAllStoreItemGroupStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(groupService.getAll(flag));
    }



    //------------------------------------------ Mas Store Vendor Type Status -------------------------------------------------------------//


    @PostMapping("/vendor-type/create")
    public ResponseEntity<?> addVendorType(@Valid @RequestBody MasVendorTypeReq request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorTypeService.create(request));
    }

    @PutMapping("/vendor-type/update/{id}")
    public ResponseEntity<?> updateVendorType(@PathVariable Long id,@Valid @RequestBody MasVendorTypeReq request){
        return  ResponseEntity.ok(vendorTypeService.updateById(id,request));
    }

    @PutMapping("/vendor-type/change/{id}")
    public ResponseEntity<?> changeVendorTypeStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(vendorTypeService.changeStatusById(id,status));
    }


    @GetMapping("/vendor-type/{id}")
    public ResponseEntity<?> getVendorTypeById(@PathVariable Long id){
        return  ResponseEntity.ok(vendorTypeService.getById(id));
    }

    @GetMapping("/vendor-type/all/{flag}")
    public ResponseEntity<?> getAllVendorTypeStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(vendorTypeService.getAll(flag));
    }




    //------------------------------------------ Mas Item Category Status -------------------------------------------------------------//


    @PostMapping("/item-category/create")
    public ResponseEntity<?> addItemCategory(@Valid @RequestBody MasItemCategoryReq request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemCategoryService.create(request));
    }

    @PutMapping("/item-category/update/{id}")
    public ResponseEntity<?> updateItemCategory(@PathVariable Long id,@Valid @RequestBody MasItemCategoryReq request){
        return  ResponseEntity.ok(itemCategoryService.updateById(id,request));
    }

    @PutMapping("/item-category/change/{id}")
    public ResponseEntity<?> changeItemCategoryStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(itemCategoryService.changeStatusById(id,status));
    }


    @GetMapping("/item-category/{id}")
    public ResponseEntity<?> getItemCategoryById(@PathVariable Long id){
        return  ResponseEntity.ok(itemCategoryService.getById(id));
    }

    @GetMapping("/item-category/all/{flag}")
    public ResponseEntity<?> getAllItemCategoryStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(itemCategoryService.getAll(flag));
    }


    //------------------------------------------ Mas Item Subcategory Status -------------------------------------------------------------//


    @PostMapping("/item-subcategory/create")
    public ResponseEntity<?> addItemSubcategory(@Valid @RequestBody MasItemSubcategoryReq request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subcategoryService.create(request));
    }

    @PutMapping("/item-subcategory/update/{id}")
    public ResponseEntity<?> updateItemSubcategory(@PathVariable Long id,@Valid @RequestBody MasItemSubcategoryReq request){
        return  ResponseEntity.ok(subcategoryService.updateById(id,request));
    }

    @PutMapping("/item-subcategory/change/{id}")
    public ResponseEntity<?> changeItemSubcategoryStatus(@PathVariable Long id,@RequestParam String status){
        return  ResponseEntity.ok(subcategoryService.changeStatusById(id,status));
    }


    @GetMapping("/item-subcategory/{id}")
    public ResponseEntity<?> getItemSubcategoryById(@PathVariable Long id){
        return  ResponseEntity.ok(subcategoryService.getById(id));
    }

    @GetMapping("/item-subcategory/all/{flag}")
    public ResponseEntity<?> getAllItemSubcategoryStatus( @PathVariable Integer flag){
        return  ResponseEntity.ok(subcategoryService.getAll(flag));
    }



}
