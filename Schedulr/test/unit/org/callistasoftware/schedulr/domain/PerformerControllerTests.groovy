package org.callistasoftware.schedulr.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(PerformerController)
@Mock(Performer)
class PerformerControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/performer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.performerInstanceList.size() == 0
        assert model.performerInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.performerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.performerInstance != null
        assert view == '/performer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/performer/show/1'
        assert controller.flash.message != null
        assert Performer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/performer/list'


        populateValidParams(params)
        def performer = new Performer(params)

        assert performer.save() != null

        params.id = performer.id

        def model = controller.show()

        assert model.performerInstance == performer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/performer/list'


        populateValidParams(params)
        def performer = new Performer(params)

        assert performer.save() != null

        params.id = performer.id

        def model = controller.edit()

        assert model.performerInstance == performer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/performer/list'

        response.reset()


        populateValidParams(params)
        def performer = new Performer(params)

        assert performer.save() != null

        // test invalid parameters in update
        params.id = performer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/performer/edit"
        assert model.performerInstance != null

        performer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/performer/show/$performer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        performer.clearErrors()

        populateValidParams(params)
        params.id = performer.id
        params.version = -1
        controller.update()

        assert view == "/performer/edit"
        assert model.performerInstance != null
        assert model.performerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/performer/list'

        response.reset()

        populateValidParams(params)
        def performer = new Performer(params)

        assert performer.save() != null
        assert Performer.count() == 1

        params.id = performer.id

        controller.delete()

        assert Performer.count() == 0
        assert Performer.get(performer.id) == null
        assert response.redirectedUrl == '/performer/list'
    }
}
