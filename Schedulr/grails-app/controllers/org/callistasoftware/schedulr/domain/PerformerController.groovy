package org.callistasoftware.schedulr.domain

import org.springframework.dao.DataIntegrityViolationException

class PerformerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [performerInstanceList: Performer.list(params), performerInstanceTotal: Performer.count()]
    }

    def create() {
        [performerInstance: new Performer(params)]
    }

    def save() {
        def performerInstance = new Performer(params)
        if (!performerInstance.save(flush: true)) {
            render(view: "create", model: [performerInstance: performerInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'performer.label', default: 'Performer'), performerInstance.id])
        redirect(action: "show", id: performerInstance.id)
    }

    def show() {
        def performerInstance = Performer.get(params.id)
        if (!performerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'performer.label', default: 'Performer'), params.id])
            redirect(action: "list")
            return
        }

        [performerInstance: performerInstance]
    }

    def edit() {
        def performerInstance = Performer.get(params.id)
        if (!performerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'performer.label', default: 'Performer'), params.id])
            redirect(action: "list")
            return
        }

        [performerInstance: performerInstance]
    }

    def update() {
        def performerInstance = Performer.get(params.id)
        if (!performerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'performer.label', default: 'Performer'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (performerInstance.version > version) {
                performerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'performer.label', default: 'Performer')] as Object[],
                          "Another user has updated this Performer while you were editing")
                render(view: "edit", model: [performerInstance: performerInstance])
                return
            }
        }

        performerInstance.properties = params

        if (!performerInstance.save(flush: true)) {
            render(view: "edit", model: [performerInstance: performerInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'performer.label', default: 'Performer'), performerInstance.id])
        redirect(action: "show", id: performerInstance.id)
    }

    def delete() {
        def performerInstance = Performer.get(params.id)
        if (!performerInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'performer.label', default: 'Performer'), params.id])
            redirect(action: "list")
            return
        }

        try {
            performerInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'performer.label', default: 'Performer'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'performer.label', default: 'Performer'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
