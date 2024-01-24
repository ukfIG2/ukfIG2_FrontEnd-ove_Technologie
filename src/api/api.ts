// api.ts
import axios from 'axios';

export async function main() {
  try {
    const response = await axios.get('http://localhost:8080/Tovar');

    if (response.data.JeAdmin == null) {
      console.log("Ak chceš pridávať veci do košíka, PRIHLÁS SA, ak si neni PRIHLÁSENÝ, REGISTRUJ SA.");
    } else {
      // Call ukazKosik function here
      // ukazKosik(response.data);
    }
  } catch (error) {
    console.error('Error:', error);
  }
}