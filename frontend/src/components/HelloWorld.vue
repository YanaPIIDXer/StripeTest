<template>
  <div>
    <h1>決済テスト</h1>
    <hr />
    <div id="card-elem"></div>
    <button @click="submit">送信</button>
  </div>
</template>

<script>
import { loadStripe } from '@stripe/stripe-js';
import conn from '../modules/BasicConnection';

export default {
  name: 'HelloWorld',
  data: function () {
    return {
      stripe: "",
      card: null
    };
  },
  mounted: async function () {
    this.stripe = await loadStripe("pk_test_51Je3M8CpeCYoB8PV01iYTyFdrOZnCnGbzxJuV8VBNoV5ucwA4lDoyOLyKxl5n80mheXFDrLcFKV3VyTmd07pQ5pN00wn2y8qrR");
    const elems = this.stripe.elements();
    this.card = elems.create("card");
    this.card.mount("#card-elem");
  },
  methods: {
    submit: async function () {
      var response = await this.stripe.createToken(this.card);
      console.log(response);
      const token = response.token.id;
      var params = new URLSearchParams();
      params.append("token", token);
      response = await conn.post("http://localhost:3000/buy", params);
      console.log(response);
    }
  }
}
</script>

<style scoped>
</style>
