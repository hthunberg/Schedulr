package org.callistasoftware.schedulr.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(TimeslotController)
@Mock(Timeslot)
class TimeslotControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/timeslot/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.timeslotInstanceList.size() == 0
        assert model.timeslotInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.timeslotInstance != null
    }

    void testSave() {
        controller.save()

        assert model.timeslotInstance != null
        assert view == '/timeslot/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/timeslot/show/1'
        assert controller.flash.message != null
        assert Timeslot.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/timeslot/list'


        populateValidParams(params)
        def timeslot = new Timeslot(params)

        assert timeslot.save() != null

        params.id = timeslot.id

        def model = controller.show()

        assert model.timeslotInstance == timeslot
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/timeslot/list'


        populateValidParams(params)
        def timeslot = new Timeslot(params)

        assert timeslot.save() != null

        params.id = timeslot.id

        def model = controller.edit()

        assert model.timeslotInstance == timeslot
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/timeslot/list'

        response.reset()


        populateValidParams(params)
        def timeslot = new Timeslot(params)

        assert timeslot.save() != null

        // test invalid parameters in update
        params.id = timeslot.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/timeslot/edit"
        assert model.timeslotInstance != null

        timeslot.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/timeslot/show/$timeslot.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        timeslot.clearErrors()

        populateValidParams(params)
        params.id = timeslot.id
        params.version = -1
        controller.update()

        assert view == "/timeslot/edit"
        assert model.timeslotInstance != null
        assert model.timeslotInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/timeslot/list'

        response.reset()

        populateValidParams(params)
        def timeslot = new Timeslot(params)

        assert timeslot.save() != null
        assert Timeslot.count() == 1

        params.id = timeslot.id

        controller.delete()

        assert Timeslot.count() == 0
        assert Timeslot.get(timeslot.id) == null
        assert response.redirectedUrl == '/timeslot/list'
    }
}
