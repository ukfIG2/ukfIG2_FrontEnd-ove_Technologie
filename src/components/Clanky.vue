<template>
    <div class="clanky">
        <div class="article">
            <h2>Welcome on Board @TUXEDO - Part 33</h2>
            <img src="https://www.tuxedocomputers.com/store_files/1/images/product_images/info_images/143743.png" alt="Article Photo">
            <p>The TUXEDO Computers team is constantly growing and important areas such as support are being expanded. At regular intervals, we introduce new and old employees to give customers a glimpse behind the scenes.</p>
            <button @click="redirectToWebsite">Prečítaj celé</button>
        </div>
        <div class="article">
            <h2>European Data Protection Day 2024</h2>
            <img src="https://www.tuxedocomputers.com/store_files/1/images/product_images/info_images/141224_1.png" alt="Article Photo">
            <p>European Data Protection Day has been held on January 28 every year since 2007</p>
            <button @click="redirectToWebsite2">Prečítaj celé</button>
        </div>
        <div class="article">
            <h2>No obsolescence: Linux instead of Windows 7 or 8. This is how Steam runs again!4</h2>
            <img src="https://www.tuxedocomputers.com/store_files/1/images/product_images/info_images/141210_1.jpg">
            <p>With a current Linux distribution such as TUXEDO OS, you can use Steam on your computer until it no longer meets the requirements. With TUXEDO OS, you can give your old Windows computer a second life as a simple gaming PC for your children.</p>
            <button @click="redirectToWebsite3">Prečítaj celé</button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Clanky',
    methods: {
        redirectToWebsite() {
            window.location.href = 'https://www.tuxedocomputers.com/en/Welcome-on-Board-TUXEDO-Part-33.tuxedo';
        }, 
        redirectToWebsite2() {
            window.location.href = 'https://www.tuxedocomputers.com/en/European-Data-Protection-Day-2024.tuxedo';
        },
        redirectToWebsite3() {
            window.location.href = 'https://www.tuxedocomputers.com/en/No-obsolescence-Linux-instead-of-Windows-7-or-8-This-is-how-Steam-runs-again-.tuxedo';
        }
    }
}
</script>

<style scoped>
    .clanky {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin-top: 50px;
    }
    .article {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 50%;
        margin-bottom: 50px;
    }
    .article img {
        width: 100%;
        height: 300px;
        object-fit: cover;
        margin-bottom: 20px;
    }
    .article button {
        width: 100%;
        height: 50px;
        background-color: #000;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: 0.3s;
    }
    .article button:hover {
        background-color: #fff;
        color: #000;
    }
    @media screen and (max-width: 768px) {
        .article {
            width: 90%;
        }
    }
</style>
        
   