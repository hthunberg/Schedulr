package org.callistasoftware.schedulr.domain

import org.springframework.dao.DataIntegrityViolationException

class SubjectOfCareController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [subjectOfCareInstanceList: SubjectOfCare.list(params), subjectOfCareInstanceTotal: SubjectOfCare.count()]
    }

    def create() {
        [subjectOfCareInstance: new SubjectOfCare(params)]
    }

    def save() {
        def subjectOfCareInstance = new SubjectOfCare(params)
        if (!subjectOfCareInstance.save(flush: true)) {
            render(view: "create", model: [subjectOfCareInstance: subjectOfCareInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), subjectOfCareInstance.id])
        redirect(action: "show", id: subjectOfCareInstance.id)
    }

    def show() {
        def subjectOfCareInstance = SubjectOfCare.get(params.id)
        if (!subjectOfCareInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), params.id])
            redirect(action: "list")
            return
        }

        [subjectOfCareInstance: subjectOfCareInstance]
    }

    def edit() {
        def subjectOfCareInstance = SubjectOfCare.get(params.id)
        if (!subjectOfCareInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), params.id])
            redirect(action: "list")
            return
        }

        [subjectOfCareInstance: subjectOfCareInstance]
    }

    def update() {
        def subjectOfCareInstance = SubjectOfCare.get(params.id)
        if (!subjectOfCareInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (subjectOfCareInstance.version > version) {
                subjectOfCareInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'subjectOfCare.label', default: 'SubjectOfCare')] as Object[],
                          "Another user has updated this SubjectOfCare while you were editing")
                render(view: "edit", model: [subjectOfCareInstance: subjectOfCareInstance])
                return
            }
        }

        subjectOfCareInstance.properties = params

        if (!subjectOfCareInstance.save(flush: true)) {
            render(view: "edit", model: [subjectOfCareInstance: subjectOfCareInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), subjectOfCareInstance.id])
        redirect(action: "show", id: subjectOfCareInstance.id)
    }

    def delete() {
        def subjectOfCareInstance = SubjectOfCare.get(params.id)
        if (!subjectOfCareInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), params.id])
            redirect(action: "list")
            return
        }

        try {
            subjectOfCareInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'subjectOfCare.label', default: 'SubjectOfCare'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
