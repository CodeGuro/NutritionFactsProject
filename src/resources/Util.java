package resources;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This util class is used to hash passwords using the SHA-256 algorithm.
 */
public class Util
{
	/**
	 * This method hashes the string.
	 * 
	 * @param string
	 *            will be hashed
	 * @return returns the hashed string
	 */
	public static String hash( String string )
	{
		try
		{
			// Create MessageDigest and encoding for input String
			MessageDigest digest = MessageDigest.getInstance( "SHA-256" );
			byte[] hash = digest.digest( string.getBytes( "UTF-8" ) );

			// Hash the Input String
			StringBuffer sb = new StringBuffer();
			for( int i = 0; i < hash.length; i++ )
			{
				sb.append( Integer.toString( ( hash[i] & 0xff ) + 0x100, 16 )
					.substring( 1 ) );
			}
			return sb.toString();
		}
		catch ( NoSuchAlgorithmException e )
		{
			e.printStackTrace();
		}
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method verifies the content of a list.
	 * 
	 * @param clazz
	 * @param c
	 * @return
	 * @throws ClassCastException
	 */
	public static < T > List< T > castList( Class< ? extends T > clazz,
		Collection< ? > c ) throws ClassCastException
	{
		List< T > r = new ArrayList< T >( c.size() );
		for( Object o : c )
			r.add( clazz.cast( o ) );
		return r;
	}
	
	public static String getFileName( String str )
	{
		String[] parts = str.split( "\\\\" );
		return parts[ parts.length - 1 ];
	}

	public static String getBasePath()
	{
		return "C:\\Users\\Administrator\\Desktop\\SixTwelveProject\\WebContent\\img";
	}
}