import Storefront from '@/components/Storefront'
import { getStorefrontData } from '@/lib/storefront'

export default async function HomePage() {
  const { categories, products } = await getStorefrontData()
  return <Storefront categories={categories} products={products} />
}
