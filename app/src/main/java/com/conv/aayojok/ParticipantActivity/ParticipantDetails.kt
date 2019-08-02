package com.conv.aayojok.ParticipantActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Telephony
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conv.aayojok.Models.ParticipantSupplier
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.activity_participant_details.*

class ParticipantDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant_details)



        nameField.text = ParticipantSupplier.name
        emailF.text = ParticipantSupplier.email
        genderF.text = ParticipantSupplier.gender
        ageF.text = ParticipantSupplier.age
        occF.text = ParticipantSupplier.occupation
        natF.text = ParticipantSupplier.nationality
        insF.text = ParticipantSupplier.institution
        phoneF.text = ParticipantSupplier.phone
        Glide.with(this).load(ParticipantSupplier.photo).apply(RequestOptions.overrideOf(250,200))
            .error(Glide.with(this).load(R.drawable.blank_pro_pic)).into(proPicF)
        saveContact.setOnClickListener {
            var email = ParticipantSupplier.email
            var phone = ParticipantSupplier.phone
            val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
                // Sets the MIME type to match the Contacts Provider
                type = ContactsContract.RawContacts.CONTENT_TYPE
            }
            intent.apply {
                // Inserts an email address
                putExtra(ContactsContract.Intents.Insert.EMAIL, email)
                /*
                 * In this example, sets the email type to be a work email.
                 * You can set other email types as necessary.
                 */
                putExtra(
                    ContactsContract.Intents.Insert.EMAIL_TYPE,
                    ContactsContract.CommonDataKinds.Email.TYPE_WORK
                )
                // Inserts a phone number
                putExtra(ContactsContract.Intents.Insert.PHONE, phone)
                /*
                 * In this example, sets the phone type to be a work phone.
                 * You can set other phone types as necessary.
                 */
                putExtra(
                    ContactsContract.Intents.Insert.PHONE_TYPE,
                    ContactsContract.CommonDataKinds.Phone.TYPE_WORK
                )
                startActivity(intent)

            }




        }



    }
}
