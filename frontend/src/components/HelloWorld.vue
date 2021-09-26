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
      const response = await this.stripe.createToken(this.card);
      const token = response.token.id;
      alert(token);
    }
  }
}
</script>

<style scoped>
</style>
