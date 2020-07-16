package com.jhonr1.location.activities

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jhonr1.location.R
import com.jhonr1.location.enums.DatabaseStatus
import com.jhonr1.location.helpers.DBHelper
import com.jhonr1.location.helpers.Playlist
import com.jhonr1.location.helpers.PlaylistRecyclerViewAdapter
import com.jhonr1.location.interfaces.IItemClick

class PlaylistActivity : BaseActivity(), IItemClick {

    private lateinit var playlists: ArrayList<Playlist>
    private lateinit var dbHelper: DBHelper
    private lateinit var btnAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var playlistRecyclerViewAdapter: PlaylistRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        displayToolbar(true)

        playlists = ArrayList()
        dbHelper = DBHelper(this@PlaylistActivity)
        playlists = dbHelper.selectAll()

        btnAdd = findViewById(R.id.btnAddPlaylist)
        btnAdd.setOnClickListener{
            addNewPlaylistDialog(DatabaseStatus.INSERT, 0, "", "","")
        }

        recyclerView = findViewById(R.id.rcvPlaylists)
        val layoutManager = LinearLayoutManager(this@PlaylistActivity)
        recyclerView.layoutManager = layoutManager
        playlistRecyclerViewAdapter = PlaylistRecyclerViewAdapter(this, playlists)
        recyclerView.adapter = playlistRecyclerViewAdapter

        readDatabase()
    }

    private fun readDatabase(){
        playlists = dbHelper.selectAll()
        playlistRecyclerViewAdapter.notifyData(playlists)
    }

    private fun addNewPlaylistDialog(status: DatabaseStatus, id: Int, txtName: String, txtCategory: String, txtDescription: String){
        val dialog = Dialog(this@PlaylistActivity, R.style.DialogFullScreen)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.fragment_add_playlist)

        val edtAddPlaylist: EditText = dialog.findViewById(R.id.edtPlaylistNameAdd)
        val edtAddPlaylistCategory: EditText = dialog.findViewById(R.id.edtPlaylistCategoryAdd)
        val edtAddPlaylistDescription: EditText = dialog.findViewById(R.id.edtPlaylistDescriptionAdd)
        val txvAddPlaylist: TextView = dialog.findViewById(R.id.txvPlaylistHeader)
        val btnAddPlaylist: Button = dialog.findViewById(R.id.btnAdd)
        val btnClosePlaylist: Button = dialog.findViewById(R.id.btnClose)

        if (status == DatabaseStatus.UPDATE){
            edtAddPlaylist.setText(txtName)
            edtAddPlaylistCategory.setText(txtCategory)
            edtAddPlaylistDescription.setText(txtDescription)
            btnAddPlaylist.text = "Update"
            txvAddPlaylist.text = "Update Playlist"
        } else{
            edtAddPlaylist.setText("")
            btnAddPlaylist.text = "Add"
            txvAddPlaylist.text = "Add New Playlist"
        }

        btnAddPlaylist.setOnClickListener{
            if(status == DatabaseStatus.UPDATE){
                dbHelper.update(
                    id.toLong(),
                    edtAddPlaylist.text.toString().trim(),
                    edtAddPlaylistCategory.text.toString().trim(),
                    edtAddPlaylistDescription.text.toString().trim()
                )
                readDatabase()
            } else if(status == DatabaseStatus.INSERT) {
                dbHelper.insert(
                    edtAddPlaylist.text.toString().trim(),
                    edtAddPlaylistCategory.text.toString().trim(),
                    edtAddPlaylistDescription.text.toString().trim()
                )
                readDatabase()
            }
            dialog.dismiss()
        }

        btnClosePlaylist.setOnClickListener{ dialog.dismiss()}

        dialog.show()
    }
    override fun onItemClick(playlist: Playlist, imgBtn: ImageButton) {

        val popup = PopupMenu(this, imgBtn)
        popup.menuInflater.inflate(R.menu.menu_playlist, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId){
                R.id.update -> addNewPlaylistDialog(DatabaseStatus.UPDATE, playlist.id, playlist.name!!, playlist.category!!, playlist.description!!)
                R.id.delete -> {
                    val deleteDialog = DeleteAlertDialog(this@PlaylistActivity, R.layout.delete_playlist, playlist)
                    deleteDialog.show("Delete Playlist Item")
                }
            }
            true
        }
        popup.show()
    }

//    Delete confirmation dialog class
    inner class DeleteAlertDialog(private val context: Context, layout: Int, private val playlist: Playlist) {

        private lateinit var alertDialog: AlertDialog
        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        private val view: View = inflater.inflate(layout,null)
        private val builder = AlertDialog.Builder(context)

        fun show(title: String){
            builder.setTitle(title)
            builder.setIcon(R.mipmap.ic_toolbar_logo)
            builder.setCancelable(true)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener{ dialog, id ->
                dbHelper.delete(playlist.id.toLong())
                readDatabase()
                dialog.cancel()
            })
            builder.setNegativeButton("CANCEL", DialogInterface.OnClickListener{
                    dialog, id -> dialog.cancel()
            })
            alertDialog = builder.setView(view).create()
            builder.show()
        }
    }
}
