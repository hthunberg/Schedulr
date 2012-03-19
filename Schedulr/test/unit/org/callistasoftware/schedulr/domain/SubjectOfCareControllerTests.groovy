package org.callistasoftware.schedulr.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(SubjectOfCareController)
@Mock(SubjectOfCare)
class SubjectOfCareControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/subjectOfCare/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.subjectOfCareInstanceList.size() == 0
        assert model.subjectOfCareInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.subjectOfCareInstance != null
    }

    void testSave() {
        controller.save()

        assert model.subjectOfCareInstance != null
        assert view == '/subjectOfCare/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/subjectOfCare/show/1'
        assert controller.flash.message != null
        assert SubjectOfCare.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/subjectOfCare/list'


        populateValidParams(params)
        def subjectOfCare = new SubjectOfCare(params)

        assert subjectOfCare.save() != null

        params.id = subjectOfCare.id

        def model = controller.show()

        assert model.subjectOfCareInstance == subjectOfCare
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/subjectOfCare/list'


        populateValidParams(params)
        def subjectOfCare = new SubjectOfCare(params)

        assert subjectOfCare.save() != null

        params.id = subjectOfCare.id

        def model = controller.edit()

        assert model.subjectOfCareInstance == subjectOfCare
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/subjectOfCare/list'

        response.reset()


        populateValidParams(params)
        def subjectOfCare = new SubjectOfCare(params)

        assert subjectOfCare.save() != null

        // test invalid parameters in update
        params.id = subjectOfCare.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/subjectOfCare/edit"
        assert model.subjectOfCareInstance != null

        subjectOfCare.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/subjectOfCare/show/$subjectOfCare.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        subjectOfCare.clearErrors()

        populateValidParams(params)
        params.id = subjectOfCare.id
        params.version = -1
        controller.update()

        assert view == "/subjectOfCare/edit"
        assert model.subjectOfCareInstance != null
        assert model.subjectOfCareInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/subjectOfCare/list'

        response.reset()

        populateValidParams(params)
        def subjectOfCare = new SubjectOfCare(params)

        assert subjectOfCare.save() != null
        assert SubjectOfCare.count() == 1

        params.id = subjectOfCare.id

        controller.delete()

        assert SubjectOfCare.count() == 0
        assert SubjectOfCare.get(subjectOfCare.id) == null
        assert response.redirectedUrl == '/subjectOfCare/list'
    }
}
