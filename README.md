# Admob-Banner-Interstitial


```XML
    <uses-permission android:name="android.permission.INTERNET" />

        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-4640395270900435~7193423416" />

```

===============


```gradle
    implementation 'com.google.android.gms:play-services-ads:20.3.0'
```


=============================

```XML

    <com.google.android.gms.ads.AdView xmlns:ads="http://schemas.android.com/apk/res-auto"
        android:id="@+id/adView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        ads:adSize="BANNER"
        ads:adUnitId="ca-app-pub-3940256099942544/6300978111"></com.google.android.gms.ads.AdView>


```

=============================


```kotlin

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
```
==========================

```kotlin


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
    
```





![SPRK_default_preset_name_custom – 1@2x](https://user-images.githubusercontent.com/51374446/132965870-d1049500-b3bc-407b-b236-36920a4b4a68.png)
