<template>
    <div class="container">
        <div class="form-container">
            <h1>Kontaktuj nás:</h1>
            <form>
                <div class="form-group">
                    <label for="name">Meno</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="surname">Priezvisko</label>
                    <input type="text" id="surname" name="surname" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="phone">Telefónne číslo</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="comment">Komentár</label>
                    <textarea id="comment" name="comment" required></textarea>
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
        <div class="map-container">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2653.701628119011!2d18.08858504615872!3d48.308589668166796!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x476b3ee4682d5deb%3A0xba2bad032d96b960!2sFakulta%20pr%C3%ADrodn%C3%BDch%20vied%20a%20informatiky!5e0!3m2!1ssk!2ssk!4v1706041386404!5m2!1ssk!2ssk" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
        <div class="contact-info">
            <h2>Predaj_nootebukov.sro</h2>
            <p>Adresa: Trieda Andreja Hlinku 603/1, 949 74 Nitra</p>
            <p>Phone: +421 999 999 999</p>
            <p>Email: nootebuky@nootebuky.sk</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'KontaktView',
    // Add your component logic here
}
</script>

<style scoped>
.container {
    display: flex;
}

.form-container {
    flex: 1;
    padding: 20px;
    background-color: #f8f9fa; /* Light grey background */
    border-radius: 10px; /* Rounded corners */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15); /* Shadow for a bit of depth */
}

.form-container label {
    display: flex;
    flex-direction: column;
    margin-bottom: 15px;
}

.map-container {
    flex: 1;
    padding: 20px;
}

.contact-info {
    background-color: #f9f9f9;
    padding: 20px;
    margin-top: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.15);
}

.contact-info h2 {
    margin-bottom: 10px;
}

.contact-info p {
    margin: 0 0 10px;
    line-height: 1.6;
}
</style>
    