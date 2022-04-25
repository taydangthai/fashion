import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PromoCodeService {

  promoCodes: any[] = [
    {
      code: 'AUTUMN',
      discountPercent: 10
    },
    {
      code: 'WINTER',
      discountPercent: 20
    }
  ];

  applyPromoCode(code: string): number {
    const promoCode = this.promoCodes.find(
      promo => promo.code === code
    );
    if (promoCode) { return promoCode.discountPercent; }

    return 0;
  }
}
