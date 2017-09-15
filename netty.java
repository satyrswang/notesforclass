model: io/data protocol/thread 

ServerSocketChaanel 
InetSocketAddress
Selector
SelectionKey.OP_ACCEPT
SelectionKey.OP_READ
handleAccept()
hadleRead()
handleConnect()
ByteBuffer
SocketChannel

netty:zero-copy
1heapmen->mainmen->SocketChannel(heapbuffers --RW to socket)
heapbuffer->socket
2combine many buffers->big buffers
3filebuffer->socket not have to write tothe mainmen by writing loop
code:
@Override
public void read(){
	final ChannelConfig              config = config();//the configuration 
	final ChannelPipeline            pipeline = pipeline();//generate pipeline with handle inside
	final ByteBufAllocator           allocator = config.getAllocator();//allocate the buffer to store the messages
	final int maxMessagePerRead      = config.getMaxMessagePerRead();//the size of the message perread from the buffer

	RecvByteBufAllocator.Handle allocHandle = this.allocHandle;//
	if(allocHandle == null){
		this.allocHandle = allocHandle =config.getRecvButeBufAllocator().newHandle();
	}
    if(!config.isAutoRead()){
    	removeReadOP();
    }

    ByteBuf byteBuf =null;
    int messages = 0;
    boolean close = false;
    try{
    	int byteBufCapacity = allocHandle.guess();
    	int totalReadAmount = 0;
    	do{
    		byteBuf = allocator.ioBuffer(byteBufCapacity);
    	}
    }
}




//if autoread is false and acitve is true ,user make a direct setautoread(falise) the just reset the status
//

ChannelConfig config = ctx.channel().config();
!config.isAutoRead() && isHandleActive(ctx) -->logger.isDebugEnabled() and ctx.attr(READ_SUSPENDED).set(false);


Unpooled and BufUtil -- ByteBufAllocator
dynamic buffer: write* method requires more space and the capacity of a dynamic buffer changes
a new method ByteBuf.capacity(int newcapacity)-- safer:you set the max capacity of the buffer
CODE: ByteBuf buf = ByteBuf.buffer(); buf.capacity(1024); buf.capacity(512);
only exception: the buffer created by wrappedBuffer()-- youcannot change the capacity 
CODE:
public static byteBuf wrappedBuffer(byte[] array){
	if(array.length == 0){
		return EMPTY_BUFFER;
	}
	return new UnpooledHeapByteBuf(ALLOC, array ,array.length);
}// byte[]-->UpooledHeapByteBuf
//or ByteBuf-->

//many byte[] -->many UpooledHeapByteBuf --> one CompositeByteBuf

CompositeByteBuf(ALLOC, false ,maxNumComponents ,  components);
//http://skyao.github.io/learning-netty/buffer/class_CompositeByteBuf.html

private final Map<AttributeKey<?>, Object> attrs = new LinkedHashMap<AttributeKey<?>, Object>();
//<?> whatdoes this mean? AttributeKey is a kind of type?
//attrs is a kind of LinkedHashMap--which returns the put in the order of your input
public <T> B attr(AttributeKey<T> key, T value) {// here T is an Object type whatever itis and the method returns the T
    if (key == null) {
        throw new NullPointerException("key");
    }
    if (value == null) {
        synchronized (attrs) {
            attrs.remove(key);// map has the method remove
        }
    } else {
        synchronized (attrs) {
            attrs.put(key, value);
        }
    }
    return (B) this;//what is this,attrs?
}

final Map<AttributeKey<?>, Object> attrs() {
    return attrs;
}//1AttributeKey<?>2 <T> B3return (B) this

//泛型和通配符。 ？代表任意java类型。只要可以作为key

// as the Channel is not registered yet we need to force the usage of the GlobalEventExecutor
return new DefaultChannelPromise(channel, GlobalEventExecutor.INSTANCE).setFailure(t);

//DefaultAddressResolverGroup.INSTANCE

private static final AddressResolverGroup< ? > DEFAULT_RESOLVER = DefaultAddressResolverGroup.INSTANCE;
private volatile AddressResolverGroup<SocketAddress> resolver = (AddressResolverGroup<SocketAddress>) DEFAULT_RESOLVER;

public Bootstrap resolver(AddressResolverGroup< ? > resolver) {
    if (resolver == null) {
        throw new NullPointerException("resolver");
    }
    this.resolver = (AddressResolverGroup<SocketAddress>) resolver;
    return this;
}


@Override
@SuppressWarnings("unchecked")
void init(Channel channel) throws Exception {
    // 取channel的ChannelPipeline
    ChannelPipeline p = channel.pipeline();
    // 增加当前Bootstrap的handle到ChannelPipeline中
    p.addLast(handler());

    // 取当前Bootstrap设置的options, 逐个设置到channel中
    final Map<ChannelOption< ? >, Object> options = options();
    synchronized (options) {
        for (Entry<ChannelOption< ? >, Object> e: options.entrySet()) {
            try {
                if (!channel.config().setOption((ChannelOption<Object>) e.getKey(), e.getValue())) {
                    logger.warn("Unknown channel option: " + e);
                }
            } catch (Throwable t) {
                logger.warn("Failed to set a channel option: " + channel, t);
            }
        }
    }

    // 同样取当前Bootstrap的attrs, 逐个设置到channel中
    final Map<AttributeKey< ? >, Object> attrs = attrs();
    synchronized (attrs) {
        for (Entry<AttributeKey< ? >, Object> e: attrs.entrySet()) {
            channel.attr((AttributeKey<Object>) e.getKey()).set(e.getValue());
        }
    }
}





















