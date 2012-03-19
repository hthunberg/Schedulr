package org.callistasoftware.schedulr.domain

import org.springframework.dao.DataIntegrityViolationException

class HealthcareFacilityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [healthcareFacilityInstanceList: HealthcareFacility.list(params), healthcareFacilityInstanceTotal: HealthcareFacility.count()]
    }

    def create() {
        [healthcareFacilityInstance: new HealthcareFacility(params)]
    }

    def save() {
        def healthcareFacilityInstance = new HealthcareFacility(params)
        if (!healthcareFacilityInstance.save(flush: true)) {
            render(view: "create", model: [healthcareFacilityInstance: healthcareFacilityInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), healthcareFacilityInstance.id])
        redirect(action: "show", id: healthcareFacilityInstance.id)
    }

    def show() {
        def healthcareFacilityInstance = HealthcareFacility.get(params.id)
        if (!healthcareFacilityInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), params.id])
            redirect(action: "list")
            return
        }

        [healthcareFacilityInstance: healthcareFacilityInstance]
    }

    def edit() {
        def healthcareFacilityInstance = HealthcareFacility.get(params.id)
        if (!healthcareFacilityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), params.id])
            redirect(action: "list")
            return
        }

        [healthcareFacilityInstance: healthcareFacilityInstance]
    }

    def update() {
        def healthcareFacilityInstance = HealthcareFacility.get(params.id)
        if (!healthcareFacilityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (healthcareFacilityInstance.version > version) {
                healthcareFacilityInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'healthcareFacility.label', default: 'HealthcareFacility')] as Object[],
                          "Another user has updated this HealthcareFacility while you were editing")
                render(view: "edit", model: [healthcareFacilityInstance: healthcareFacilityInstance])
                return
            }
        }

        healthcareFacilityInstance.properties = params

        if (!healthcareFacilityInstance.save(flush: true)) {
            render(view: "edit", model: [healthcareFacilityInstance: healthcareFacilityInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), healthcareFacilityInstance.id])
        redirect(action: "show", id: healthcareFacilityInstance.id)
    }

    def delete() {
        def healthcareFacilityInstance = HealthcareFacility.get(params.id)
        if (!healthcareFacilityInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), params.id])
            redirect(action: "list")
            return
        }

        try {
            healthcareFacilityInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'healthcareFacility.label', default: 'HealthcareFacility'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
