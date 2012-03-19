package org.callistasoftware.schedulr.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(HealthcareFacilityController)
@Mock(HealthcareFacility)
class HealthcareFacilityControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/healthcareFacility/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.healthcareFacilityInstanceList.size() == 0
        assert model.healthcareFacilityInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.healthcareFacilityInstance != null
    }

    void testSave() {
        controller.save()

        assert model.healthcareFacilityInstance != null
        assert view == '/healthcareFacility/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/healthcareFacility/show/1'
        assert controller.flash.message != null
        assert HealthcareFacility.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/healthcareFacility/list'


        populateValidParams(params)
        def healthcareFacility = new HealthcareFacility(params)

        assert healthcareFacility.save() != null

        params.id = healthcareFacility.id

        def model = controller.show()

        assert model.healthcareFacilityInstance == healthcareFacility
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/healthcareFacility/list'


        populateValidParams(params)
        def healthcareFacility = new HealthcareFacility(params)

        assert healthcareFacility.save() != null

        params.id = healthcareFacility.id

        def model = controller.edit()

        assert model.healthcareFacilityInstance == healthcareFacility
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/healthcareFacility/list'

        response.reset()


        populateValidParams(params)
        def healthcareFacility = new HealthcareFacility(params)

        assert healthcareFacility.save() != null

        // test invalid parameters in update
        params.id = healthcareFacility.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/healthcareFacility/edit"
        assert model.healthcareFacilityInstance != null

        healthcareFacility.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/healthcareFacility/show/$healthcareFacility.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        healthcareFacility.clearErrors()

        populateValidParams(params)
        params.id = healthcareFacility.id
        params.version = -1
        controller.update()

        assert view == "/healthcareFacility/edit"
        assert model.healthcareFacilityInstance != null
        assert model.healthcareFacilityInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/healthcareFacility/list'

        response.reset()

        populateValidParams(params)
        def healthcareFacility = new HealthcareFacility(params)

        assert healthcareFacility.save() != null
        assert HealthcareFacility.count() == 1

        params.id = healthcareFacility.id

        controller.delete()

        assert HealthcareFacility.count() == 0
        assert HealthcareFacility.get(healthcareFacility.id) == null
        assert response.redirectedUrl == '/healthcareFacility/list'
    }
}
