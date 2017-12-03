import { BaseEntity } from './../../shared';

export class Item implements BaseEntity {
    constructor(
        public id?: number,
        public itemTitle?: string,
        public itemDescription?: string,
        public itemImageContentType?: string,
        public itemImage?: any,
        public basePrice?: number,
        public itemAttributes?: BaseEntity[],
        public subCategoryId?: number,
        public accountId?: number,
        public saleId?: number,
    ) {
    }
}
