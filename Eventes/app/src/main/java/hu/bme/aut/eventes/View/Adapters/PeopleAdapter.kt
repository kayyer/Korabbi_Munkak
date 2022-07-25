package hu.bme.aut.eventes.View.Adapters
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.bme.aut.eventes.Interfaces.Contracts.IView.IPeopleContract
import hu.bme.aut.eventes.View.Activities.ProfileActivity
import hu.bme.aut.eventes.R
import hu.bme.aut.eventes.databinding.ItemPersonBinding

class PeopleAdapter(private val peoplePres: IPeopleContract.IPresenter) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.PeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        peoplePres.bindViewHolder(holder,position)

        holder.profileBtn.setOnClickListener{
            peoplePres.profileBtnClick(position,holder)
        }
    }

    override fun getItemCount(): Int = peoplePres.getPeopleCount()


    inner class PeopleViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView),IPeopleContract.IAdapter{

        var binding = ItemPersonBinding.bind(itemView)
        var profileBtn = binding.ProfileBtn
        var name = binding.name

        override fun setProfileBtn(uri: Uri?){
            if(uri != null)
                Picasso.get().load(uri).into(profileBtn)
            else
                profileBtn.setImageResource(R.drawable.dpp)
        }

        override fun setName(name: String?){
            this.name.text = name
        }

        override fun openProfileActivity(
            chatAvailable: Boolean,
            friendArray: ArrayList<String>?,
            GroupChat: Boolean,
        ) {
            val profileIntent = Intent(itemView.context, ProfileActivity::class.java)
            profileIntent.putExtra("GroupChat", GroupChat)
            profileIntent.putExtra("user", friendArray)
            profileIntent.putExtra("chatAvailable",chatAvailable)
            itemView.context.startActivity(profileIntent)
        }
    }
}



