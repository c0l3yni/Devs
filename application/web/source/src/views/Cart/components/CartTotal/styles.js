export default {
    underMinError: 'text-red-600',
    paddingBetweenColumns: [
        'px-6',
        'pt-4',
        'pb-2'
    ].join(" "),
    totalBorder: [
        'fixed',
        'border',
        'border-grey-500',
        'flex-col',
        'justify-between',
        'items-center',
        'w-96'
    ].join(" "),
    totalContainer: [
        'flex',
        'items-center',
        'justify-between'
    ].join(" "),
    totalTitle: [
        'text-gray-700',
        'font-bold',
        'text-xl'
    ].join(" "),
    checkoutButton: ({isTotalOutOfBounds}) => [
        isTotalOutOfBounds ? 'bg-gray-400' : 'bg-blue-500',
        isTotalOutOfBounds ? '' : 'hover:bg-blue-700',
        'text-white',
        'font-bold',
        'py-2',
        'px-4',
        'rounded'
    ].join(" ")
}