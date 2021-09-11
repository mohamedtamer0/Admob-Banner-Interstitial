package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadBannerAd()
        loadInterAd()


    }



    private fun loadInterAd() {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })


        btn_ad.setOnClickListener {
            if (mInterstitialAd != null) {

                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        val intent = Intent(this@MainActivity,MainActivity2::class.java)
                        startActivity(intent)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {

                    }

                    override fun onAdShowedFullScreenContent() {

                        mInterstitialAd = null
                    }
                }


                mInterstitialAd?.show(this)
            } else {
                Toast.makeText(this,"The interstitial ad wasn't ready yet.",Toast.LENGTH_LONG).show()
            }
        }


    }



    private fun loadBannerAd(){
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }


}

