import org.callistasoftware.schedulr.domain.HealthcareFacility
import org.callistasoftware.schedulr.domain.Performer
import org.callistasoftware.schedulr.domain.SubjectOfCare

class BootStrap {

    def init = { servletContext ->

		// Vårdcentraler
		if (!HealthcareFacility.count()) {
			new HealthcareFacility(healthcareFacility: "HSA-VKK123", healthcareFacilityName: "Vårdcentralen kusten, Kärna").save(failOnError: true)
			new HealthcareFacility(healthcareFacility: "HSA-VKM345", healthcareFacilityName: "Vårdcentralen kusten, Marstrand").save(failOnError: true)
			new HealthcareFacility(healthcareFacility: "HSA-VKY567", healthcareFacilityName: "Vårdcentralen kusten, Ytterby").save(failOnError: true)
		}

		//Personal
		if (!Performer.count()) {
			new Performer(firstName: "Stina", lastName: "Karlsson", title: "Läkare", performerId: "HSA-12345").save(failOnError: true)
			new Performer(firstName: "Lotta", lastName: "Olsson", title: "Läkare", performerId: "HSA-54321").save(failOnError: true)
			new Performer(firstName: "Oskar", lastName: "Nilsson", title: "Läkare", performerId: "HSA-34567").save(failOnError: true)
			new Performer(firstName: "Peter", lastName: "Andersson", title: "Sjuksköterska", performerId: "HSA-87654").save(failOnError: true)
			new Performer(firstName: "Lars", lastName: "Fredriksson", title: "Sjuksköterska", performerId: "HSA-35674").save(failOnError: true)
		}

		//Patienter
		if (!SubjectOfCare.count()) {
			new SubjectOfCare(subjectOfCareId: "19691202-6066", firstName: "Jan", lastName: "Jansson", middleName: "Ove", phone: "0451-3553531", email: "jan.jansson@fejkepost.something", homeAddress: "Tungegatan 32, 44254, Ytterby").save(failOnError: true)
			new SubjectOfCare(subjectOfCareId: "19751026-6849",firstName: "Per", lastName: "Persson", middleName: "", phone: "021-6213418", email: "per.persson@fejkepost.something",homeAddress: "Bygatan 145, 44254, Kärna").save(failOnError: true)
			new SubjectOfCare(subjectOfCareId: "19701217-7650",firstName: "Peter", lastName: "Petersson", middleName: "", phone: "040-3191163", email: "peter.petersson@fejkepost.something",homeAddress: "Sparråsvägen 21, 44254, Ytterby").save(failOnError: true)
			new SubjectOfCare(subjectOfCareId: "19700406-4155",firstName: "Olle", lastName: "Granberg", middleName: "", phone: "0940-4230632", email: "olle.granberg@dodgit.com",homeAddress: "Holmtebo ekbacken, 91290, Marstrand").save(failOnError: true)
		}

	}
    def destroy = {
    }
}
